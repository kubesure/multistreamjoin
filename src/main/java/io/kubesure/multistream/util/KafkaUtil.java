package io.kubesure.multistream.util;

import java.util.Properties;

import org.apache.avro.specific.SpecificRecord;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.formats.avro.registry.confluent.ConfluentRegistryAvroDeserializationSchema;
import org.apache.flink.formats.avro.registry.confluent.ConfluentRegistryAvroSerializationSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class KafkaUtil {

    public static <T> KafkaProducer<String, T> newKakfaAvroProducer() {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		//props.setProperty("zookeeper.connect", "localhost:2181");
		props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
		props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
		KafkaProducer<String, T> producer = new KafkaProducer<String,T>(props);
		return producer;
    }

    public static <T extends SpecificRecord> FlinkKafkaProducer<T> newFlinkAvroProducer
								(String topic, Class<T> tclass,String outputSchema,ParameterTool parameterTool){
		FlinkKafkaProducer<T> kafkaProducer = new FlinkKafkaProducer<>(
												topic,  
												ConfluentRegistryAvroSerializationSchema.forSpecific(
												tclass,
												outputSchema,
												parameterTool.getRequired("schema.registry.url")),
												parameterTool.getProperties()
											);
		return kafkaProducer;									
	}

    public static <T extends SpecificRecord> FlinkKafkaConsumer<T> newFlinkAvroConsumer
                                                (String topic,Class<T> tclass, ParameterTool parameterTool) {
		return	new FlinkKafkaConsumer<>(
					topic,  
					ConfluentRegistryAvroDeserializationSchema.forSpecific(
					tclass,
					parameterTool.getRequired("schema.registry.url")), 
					parameterTool.getProperties());
	}
    
}
