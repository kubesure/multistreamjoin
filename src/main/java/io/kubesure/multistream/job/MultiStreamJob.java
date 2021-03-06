package io.kubesure.multistream.job;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.KeyedCoProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.kubesure.multistream.datatypes.Deal;
import io.kubesure.multistream.datatypes.Payment;
import io.kubesure.multistream.datatypes.Purchase;
import io.kubesure.multistream.util.KafkaUtil;
import io.kubesure.multistream.util.Util;

/**
 * @author Prashant Patel
 * MultiStreamJob connects two stream Purchase and Payment to match a Deal. The {@code DealMatcher} matches 
 * {@link Purchase} to {@link Payment} on tranaction id. The match waits for each deal to arrive for a 
 * timer.delay.time. Events arriving later than timer.delay.time will processed to late events Topic 
 * kafka.purchase.unmatched.topic and kafka.payment.unmatched.topic for re-processsing by a pipleline. The 
 * events can be push in MultiStreamJob from a re-processing pipeline based on retries.    
 */

public class MultiStreamJob {

	private static final Logger log = LoggerFactory.getLogger(MultiStreamJob.class);
	public static ParameterTool parameterTool;

	static final OutputTag<Purchase> unmatchedPurchases = new OutputTag<Purchase>("unmatchedPurchases") {
		private static final long serialVersionUID = 13434343455656L;
	};
	static final OutputTag<Payment> unmatchedPayments = new OutputTag<Payment>("unmatchedPayments") {
		private static final long serialVersionUID = 13434343455656L;
	};

	public static void main(String[] args) throws Exception {
		parameterTool = Util.readProperties();
		StreamExecutionEnvironment env = Util.prepareExecutionEnv(parameterTool);

		// Comment for processing time 		
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
		//env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);

		// uncomment for unit testing
		//DataStream<Purchase> purchaseInput = env.addSource(new PurchaseSource(10,1000l));
		//DataStream<Payment>  paymentInput  = env.addSource(new PaymentSource(10,3000l));

		// Comment for unit testing
		//Read from purchase Topic and Map to Purchase 
		DataStream<Purchase> purchaseInput = env
				.addSource(KafkaUtil.<Purchase>newFlinkAvroConsumer(
							parameterTool.getRequired("kafka.purchase.input.topic"),
							Purchase.class, parameterTool))
				.uid("Purchase Input"); 
		
		// Comment for unit testing		
		//Read from payment Topic and Map to Payment
		DataStream<Payment> paymentInput = env
				.addSource(KafkaUtil.<Payment>newFlinkAvroConsumer(
					parameterTool.getRequired("kafka.payment.input.topic"),
					Payment.class, parameterTool))
				.uid("Payment Input");

		//Connect purchase to payment, keyby transaction id and match payment to purchase for a deal.		
		SingleOutputStreamOperator<Deal> processed = purchaseInput
				.connect(paymentInput)
				.keyBy((Purchase::getTransactionID), (Payment::getTransactionID))
				.process(new DealMatcher(parameterTool.getLong("timer.delay.time")))
				.uid("Match Deal");

		if (log.isInfoEnabled()) {
			// TODO: print() is logging purchase instead of Deal after Avro generation 
			// reason unknown. Need to implement a custom logger for print().
			//processed.getSideOutput(unmatchedPurchases).print();
			//processed.getSideOutput(unmatchedPayments).print();
			//processed.print();
		}		

		//Push matched deal to deal topic
		processed.addSink(KafkaUtil.<Deal>newFlinkAvroProducer(
									parameterTool.getRequired("kafka.deal.topic"),
									Deal.class,
									parameterTool.getRequired("schema.registry.deal.subject"),
				   					parameterTool))
				 .uid("Deal");

		//Push late or unmatched purchase w.r.t to payment which came later than timer.delay.time   		 
		processed
				.getSideOutput(unmatchedPurchases)
				.addSink(KafkaUtil.<Purchase>newFlinkAvroProducer(
									parameterTool.getRequired("kafka.purchase.unmatched.topic"),
									Purchase.class,
									parameterTool.getRequired("schema.registry.purchase.subject"),
									parameterTool))
				.uid("UnMatched purchases");
							
		//Push late or unmatched payment w.r.t to purchase which came later than timer.delay.time		
		processed
				.getSideOutput(unmatchedPayments)
				.addSink(KafkaUtil.newFlinkAvroProducer(
									parameterTool.getRequired("kafka.payment.unmatched.topic"),
									Payment.class,
									parameterTool.getRequired("schema.registry.payment.subject"),
						  	 		parameterTool))
				.uid("UnMatched payments");					

		env.execute("Multistream Event Time Join");
	}

	/**
	 * The connected transactionid keyedby matcher, matches purchase to a payment, late events are 
	 * caught in ontimer after timer.delay.time. Works with both event time and processsing time. 
	 */
	public static class DealMatcher extends KeyedCoProcessFunction<String, Purchase, Payment, Deal> {

		private static final long serialVersionUID = 13434343455656L;
		//private static final Logger log = LoggerFactory.getLogger(DealMatcher.class);

		private ValueState<Purchase> purchaseState;
		private ValueState<Payment> paymentState;
		private transient ValueState<Long> timerState;
		private long timerDelay;
		
		public DealMatcher(long timerDelay){
			this.timerDelay = timerDelay;
		}

		@Override
		public void open(Configuration config) {
			purchaseState = getRuntimeContext().getState(new ValueStateDescriptor<>("saved purchase", TypeInformation.of(Purchase.class)));
			paymentState = getRuntimeContext().getState(new ValueStateDescriptor<>("saved payment", TypeInformation.of(Payment.class)));
			timerState = getRuntimeContext().getState(new ValueStateDescriptor<>("timer state", Types.LONG));
		}

		@Override
		public void processElement1(Purchase purchase,
				KeyedCoProcessFunction<String, Purchase, Payment, Deal>.Context ctx, Collector<Deal> out)
				throws Exception {
			//Check if payment for purchase has already arrived 
			// if yes match is a sucessfull deal 
			// if no store purchase for matching when payment arrives
			Payment payment = paymentState.value();
			if (payment != null) {
				// if payment arrived match the deal and push out to sink
				Deal d = new Deal(purchase, payment);
				cleanUp(ctx);
				out.collect(d);
			} else {
				// if payment has not arrived store in purchase state for processelement2 
				//to match when payment arrives
				purchaseState.update(purchase);	

				//set timer with a timer.delay.time

				//uncomment for event time or processing time
				long delay = purchase.getTransactionDate() + timerDelay;
				//long delay = ctx.timerService().currentProcessingTime() + timerDelay;

				timerState.update(delay);

				//uncomment for event time or processing time
				// ctx.timerService().registerProcessingTimeTimer(delay);
				ctx.timerService().registerEventTimeTimer(delay);
			}
		}

		@Override
		public void processElement2(Payment payment,
				KeyedCoProcessFunction<String, Purchase, Payment, Deal>.Context ctx, Collector<Deal> out)
				throws Exception {

			// Check if purchase for payment has already arrived 
			// if yes match is a sucessfull deal 
			// if no store payment for matching when purchase arrives
			Purchase purchase = purchaseState.value();
			if (purchase != null) {
				// if purchase arrived match the deal and push out for sink
				Deal d = new Deal(purchase, payment);
				cleanUp(ctx);
				out.collect(d);
			} else {
				//if purchase has not arrived store in payment state for processelement1 to 
				//match when purchase arrives
				paymentState.update(payment);

				//set timer with a timer.delay.time

				//uncomment for event time or processing time
				long delay = payment.getTransactionDate() + timerDelay;
				//long delay = ctx.timerService().currentProcessingTime() + timerDelay;
				timerState.update(delay);

				//uncomment for event time or processing time
				//ctx.timerService().registerProcessingTimeTimer(delay);
				ctx.timerService().registerEventTimeTimer(delay);
			}
		}

		/**
		 * Called on timer expiry. Late events or ummatched event is push to side output.  
		 */
		@Override
		public void onTimer(long t, OnTimerContext ctx, Collector<Deal> out) throws Exception {

			Purchase purchase = purchaseState.value();
			Payment payment = paymentState.value(); 

			boolean hasPurchaseArrived = purchase != null;
			boolean hasPaymentArrived  = payment != null;

			//push unmatch or late arrived purchase to sideoutput > kafka.purchase.unmatched.topic
			if ((hasPurchaseArrived && !hasPaymentArrived)) {
				ctx.output(unmatchedPurchases, purchaseState.value());
				cleanUp(ctx);
			}

			//push unmatch or late arrived purchase to sideoutput > kafka.payment.unmatched.topic
			if ((!hasPurchaseArrived && hasPaymentArrived)) {
				ctx.output(unmatchedPayments, paymentState.value());
				cleanUp(ctx);
			}
		}

		//clean up keyed resources
		private void cleanUp(Context ctx) throws Exception {
			// delete timer

			//uncomment for event time or processing time
			ctx.timerService().deleteEventTimeTimer(timerState.value());
			// ctx.timerService().deleteProcessingTimeTimer(timerState.value());

			// clean up all state
			timerState.clear();
			purchaseState.clear();
			paymentState.clear();
		}
	}
}
