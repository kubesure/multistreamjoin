package io.kubesure.multistream;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.kubesure.multistream.util.KafkaUtil;

public class CommonThread<T> {

    private static final Logger log = LoggerFactory.getLogger(CommonThread.class);

    /*protected void send(String payload,String topic) throws Exception {
        KafkaProducer<String,String> producer = Util.newKakfaProducer();
        ProducerRecord<String,String> producerRec =
             new ProducerRecord<String,String>(topic, payload);
        try {
        	producer.send(producerRec).get();
        }catch(Exception kse){
        	log.error("Error writing message to dead letter Q", kse);
        }
    }*/

    protected <T> void sendPayload(Object obj,Class<T> tclass,String topic) throws Exception {
        KafkaProducer<String,T> producer = KafkaUtil.<T>newKakfaAvroProducer();
        ProducerRecord<String,T> producerRec =
             new ProducerRecord<String,T>(topic,tclass.cast(obj));
        try {
        	producer.send(producerRec).get();
        }catch(Exception kse){
        	log.error("Error writing message to " + tclass.getName(), kse);
        }
    }
}