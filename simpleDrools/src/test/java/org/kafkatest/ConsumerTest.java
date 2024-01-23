package org.kafkatest;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Date: 2022/8/15 10:58
 * @Description: Kafka消费者
 */
public class ConsumerTest {

    private static final Properties properties = new Properties();
    private static final KafkaConsumer<String, String> consumer;

    static {
        properties.put("bootstrap.servers", "192.168.43.201:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "test");
        properties.put("enable.auto.commit", "true");
        consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Collections.singleton("topic0-1"));
    }

    public static void main(String[] args) {
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("topic = %s ,partition = %s ,keys = %s ,values = %s", record.topic(), record.partition(),
                            record.key(), record.value());
                }
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (consumer != null) {
                consumer.close();
            }
        }
    }
}
