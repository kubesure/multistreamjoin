package io.kubesure.multistream.job;

import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.kubesure.multistream.datatypes.Customer;
import io.kubesure.multistream.datatypes.JoinedEvents;
import io.kubesure.multistream.datatypes.Trade;
import io.kubesure.multistream.sources.Sources;

public class MultiStreamJob {

	private static final Logger log = LoggerFactory.getLogger(MultiStreamJob.class);
	
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		DataStream<Customer> customerStream = Sources.customerSource(env); 
		DataStream<Trade> tradeStream = Sources.tradeSource(env);
		//DataStream<Payment> paymentStream = Sources.paymentSource(env);

		DataStream<JoinedEvents> joinedStream = tradeStream
		.keyBy("cif")
		.connect(customerStream.keyBy("cif"))
		.process(new EventTimeJoin());

		//joinedStream.print();

		env.execute("Multistream Event Time Join");
	}
}
