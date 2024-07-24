package org.kafkatest;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * @author Julyan
 * @version V1.0
 * @Date: 2022/8/14 22:08
 * @Description: 生产者
 */
public class ProducerTest {

    static final Properties properties = new Properties();
    static KafkaProducer<String, String> producer;

    static {
        properties.put("bootstrap.servers", "192.168.43.201:9092");
        properties.put("group.id", "test");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(properties);
    }

    public static void main(String[] args) {
        String topicName = "topic0-1";
        try {
            RecordMetadata name = producer.send(new ProducerRecord<String, String>(topicName, "name", "{\"success\": true}")).get();
            System.out.println(name.partition());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }

    }
}
