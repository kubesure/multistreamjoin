package io.kubesure.multistream.util;

import java.util.Properties;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class KafkaUtil {

	public static FlinkKafkaProducer<String> newFlinkKafkaProducer(String topic, ParameterTool parameterTool) {
		// TODO: replace depricated constructor
		FlinkKafkaProducer<String> kafkaProducer = new FlinkKafkaProducer<String>(
				parameterTool.getRequired(topic),
				new SimpleStringSchema(),
				parameterTool.getProperties());
		kafkaProducer.setWriteTimestampToKafka(true);
		return kafkaProducer;
	}

	public static KafkaProducer<String, String> newKakfaProducer(ParameterTool parameterTool) {
		Properties properties = parameterTool.getProperties();
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		return producer;
	}

	public static FlinkKafkaConsumer<String> newFlinkKafkaConsumer(String topic, ParameterTool parameterTool) {
		return new FlinkKafkaConsumer<>(
			parameterTool.getRequired(topic), 
			new SimpleStringSchema(), 
			parameterTool.getProperties());
	}
    
}