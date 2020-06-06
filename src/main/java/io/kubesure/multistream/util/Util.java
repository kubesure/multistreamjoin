package io.kubesure.multistream.util;

import java.util.Properties;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.kafka.clients.producer.KafkaProducer;

public class Util {

	public static KafkaProducer<String,String> newKakfaProducer(){
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("zookeeper.connect", "localhost:2181");
		properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
		KafkaProducer<String,String> producer = new KafkaProducer<String,String>(properties); 
		return producer;
	}

	public static ParameterTool readProperties() throws Exception {
		ParameterTool parameterTool = ParameterTool.fromPropertiesFile
								(Util.class.getClassLoader().getResourceAsStream("stream.properties"));
		return parameterTool;
	}
	
	public static StreamExecutionEnvironment prepareExecutionEnv(ParameterTool parameterTool)
		throws Exception {

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.getConfig().setAutoWatermarkInterval(500l);
		// env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(4, 10000));
		// env.enableCheckpointing(5000); // create a checkpoint every 5 seconds
		env.getConfig().setGlobalJobParameters(parameterTool); // make parameters available in the web interface
		return env;
	}
}