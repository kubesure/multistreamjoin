package io.kubesure.multistream.job;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class MultiStreamJob {
	
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		/*DataStream<Transaction> transactions = env
			.addSource(new FlinkKa);


		alerts
			.addSink(new Sink())
			.name("join-sink");
        */
		env.execute("Multistream Join");
	}
}
