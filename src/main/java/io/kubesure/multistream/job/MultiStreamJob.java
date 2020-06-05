package io.kubesure.multistream.job;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
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
	
	public static void main(String[] args) throws Exception {
		parameterTool = Util.readProperties();
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		DataStream<Purchase> purchaseStream = env
		            	.addSource(
							new FlinkKafkaConsumer<>(
								parameterTool.getRequired("kafka.purchase.input.topic"), 
								new SimpleStringSchema(), 
								parameterTool.getProperties()))
						.flatMap(new JSONToPurchase())
						.assignTimestampsAndWatermarks
							(new BoundedOutOfOrdernessTimestampExtractor<Purchase>(Time.seconds(10)) {
								private static final long serialVersionUID = -686876346234753642L;	
								@Override
								public long extractTimestamp(Purchase element) {
									if (log.isInfoEnabled()) {
										log.info("New purchase event time     - {}", TimeUtil.ISOString(element.getTransactionDate().getMillis()));	
									}
									return element.getTransactionDate().getMillis();
								}
						}).name("Purchase Input");

						DataStream<Payment> paymentStream = env
		            	.addSource(
							new FlinkKafkaConsumer<>(
								parameterTool.getRequired("kafka.payment.input.topic"), 
								new SimpleStringSchema(), 
								parameterTool.getProperties()))
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


		DataStream<Deal> dealStream = purchaseStream
		 				.keyBy(r -> r.getTransactionID())
						.connect(paymentStream)
						.process(new EventTimeJoin());

		dealStream.print();			

		env.execute("Multistream Event Time Join");
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
