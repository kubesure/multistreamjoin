package io.kubesure.multistream.job;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.KeyedCoProcessFunction;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.kubesure.multistream.datatypes.Deal;
import io.kubesure.multistream.datatypes.Payment;
import io.kubesure.multistream.datatypes.Purchase;
import io.kubesure.multistream.util.Convertor;
import io.kubesure.multistream.util.KafkaUtil;
import io.kubesure.multistream.util.TimeUtil;
import io.kubesure.multistream.util.Util;

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
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		DataStream<Purchase> purchaseInput = env
		        .addSource(KafkaUtil.newFlinkKafkaConsumer("kafka.purchase.input.topic", parameterTool))
				.flatMap(new JSONToPurchase())
				.assignTimestampsAndWatermarks
					(new BoundedOutOfOrdernessTimestampExtractor<Purchase>(Time.seconds(10)) {
						private static final long serialVersionUID = -686876346234753642L;	
						@Override
						public long extractTimestamp(Purchase element) {
							if(log.isInfoEnabled()) {
								log.info("New Event Time     - {}", TimeUtil.ISOString(element.getTransactionDate().getMillis()));	
							}
							return element.getTransactionDate().getMillis();
						}
				}).name("Purchase Input");

		DataStream<Payment> paymentInput = env
		        .addSource(KafkaUtil.newFlinkKafkaConsumer("kafka.payment.input.topic", parameterTool))
				.flatMap(new JSONToPayment())
				.assignTimestampsAndWatermarks
					(new BoundedOutOfOrdernessTimestampExtractor<Payment>(Time.seconds(10)) {
						private static final long serialVersionUID = -686876346234753642L;	
						@Override
						public long extractTimestamp(Payment element) {
							if(log.isInfoEnabled()) {
								log.info("New Event Time     - {}", TimeUtil.ISOString(element.getTransactionDate().getMillis()));	
							}
							return element.getTransactionDate().getMillis();
						}
				}).name("Payment Input");						

		SingleOutputStreamOperator<Deal> processed = purchaseInput
						.connect(paymentInput)
						.keyBy((Purchase::getTransactionDate),(Payment::getTransactionDate))
						.process(new DealMatcher());		

		processed.getSideOutput(unmatchedPayments).print();

		processed.getSideOutput(unmatchedPayments).print();
						
		processed.print(); 

		

		env.execute("Multistream Event Time Join");
	}

	public static class DealMatcher extends KeyedCoProcessFunction<String, Purchase, Payment, Deal> {

		private static final long serialVersionUID = 13434343455656L;
		private static final Logger log = LoggerFactory.getLogger(DealMatcher.class);
		
		private ValueState<Purchase> purchaseState = null;
		private ValueState<Payment> paymentState = null;
	
		@Override
		public void open(Configuration config) {
			purchaseState = getRuntimeContext().getState(new ValueStateDescriptor<>("saved purchase", Purchase.class));
			paymentState = getRuntimeContext().getState(new ValueStateDescriptor<>("saved payment", Payment.class));
		}
	
		@Override
		public void processElement1(Purchase purchase, 
									KeyedCoProcessFunction<String, Purchase, Payment,Deal>.Context ctx,
									Collector<Deal> out) throws Exception {
			Payment payment = paymentState.value();
			if (payment != null) {
				paymentState.clear();
				ctx.timerService().deleteEventTimeTimer(payment.getEventTime());
				out.collect(new Deal(purchase,payment));
			} else {
				purchaseState.update(purchase);
				ctx.timerService().registerEventTimeTimer(purchase.getEventTime());
			}
		}
	
		@Override
		public void processElement2(Payment payment, 
									KeyedCoProcessFunction<String, Purchase, Payment, Deal>.Context ctx,
									Collector<Deal> out) throws Exception {
	
			Purchase purchase = purchaseState.value();
			if (purchase != null) {
				purchaseState.clear();
				ctx.timerService().deleteEventTimeTimer(purchase.getEventTime());
				out.collect(new Deal(purchase,payment));
			} else {
				purchaseState.update(purchase);
				ctx.timerService().registerEventTimeTimer(payment.getEventTime());
			}  
		}
	
		@Override
		public void onTimer(long t, OnTimerContext ctx, Collector<Deal> out) throws Exception {
			if (purchaseState.value() != null) {
				ctx.output(unmatchedPurchases, purchaseState.value());
				purchaseState.clear();
			}
			if (paymentState.value() != null) {
				ctx.output(unmatchedPayments, paymentState.value());
				paymentState.clear();
			}        
		}
	}

	private static class JSONToPurchase 
									implements FlatMapFunction<String, Purchase> {

		private static final long serialVersionUID = -686876771747690202L;		

		@Override
		public void flatMap(String prospectCompany, Collector<Purchase> collector) {

			KafkaProducer<String, String> producer = null;
			ProducerRecord<String, String> producerRec = null;

			try {
				Purchase p = Convertor.convertToPurchase(prospectCompany);
				collector.collect(p);
			} catch (Exception e) {
				log.error("Error deserialzing Prospect company", e);
				producer = KafkaUtil.newKakfaProducer(parameterTool);
				// TODO: Define new error message payload instead of dumping exception message on DLQ
				producerRec = new ProducerRecord<String, String>
										(parameterTool.getRequired("kafka.purchase.DQL.topic"), 
										e.getMessage());
				// TODO: Implement async send
				try {
					producer.send(producerRec).get();
				} catch (Exception kse) {
					log.error("Error writing message to dead letter Q", kse);
				}
			} finally {
				if (producer != null) {
					producer.close();
				}
			}
		}
	}
	
	private static class JSONToPayment 
									implements FlatMapFunction<String, Payment> {

		private static final long serialVersionUID = -686876771747690202L;		

		@Override
		public void flatMap(String payment, Collector<Payment> collector) {

			KafkaProducer<String, String> producer = null;
			ProducerRecord<String, String> producerRec = null;

			try {
				Payment p = new Payment();
				collector.collect(p);
			} catch (Exception e) {
				log.error("Error deserialzing Prospect company", e);
				producer = KafkaUtil.newKakfaProducer(parameterTool);
				// TODO: Define new error message payload instead of dumping exception message on DLQ
				producerRec = new ProducerRecord<String, String>
										(parameterTool.getRequired("kafka.payment.DQL.topic"), 
										e.getMessage());
				// TODO: Implement async send
				try {
					producer.send(producerRec).get();
				} catch (Exception kse) {
					log.error("Error writing message to dead letter Q", kse);
				}
			} finally {
				if (producer != null) {
					producer.close();
				}
			}
		}
	}
}
