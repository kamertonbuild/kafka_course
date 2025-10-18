package ru.yandex.course.kafka.unit1;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * <a href="https://practicum.yandex.ru/learn/kafka/courses/83ca50bc-5dce-42cd-b629-997c971f1765/sprints/713562/topics/cfde3128-d6eb-4b4e-ba60-a0999d162a79/lessons/4600901b-93c9-402f-aaee-bb7d538682bf/">Настройка репликации через код</a>
 */
public class Replication {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9081");
        props.put(ProducerConfig.ACKS_CONFIG, "all"); // Для синхронной репликации
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        props.put(TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG, "2"); // Минимум 2 реплики должны подтвердить запись

        try {
            KafkaProducer<String, String> producer = new KafkaProducer<>(props);
            ProducerRecord<String, String> record = new ProducerRecord<>("my-topic-9081", "key", "value");
            producer.send(record);
            producer.close();
        } catch (Exception e) {
            System.err.println("=======> " + e.getMessage());
        }
    }
}
