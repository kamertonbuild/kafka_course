package ru.yandex.course.kafka.unit1;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * <a href="https://practicum.yandex.ru/learn/kafka/courses/83ca50bc-5dce-42cd-b629-997c971f1765/sprints/713562/topics/cfde3128-d6eb-4b4e-ba60-a0999d162a79/lessons/4600901b-93c9-402f-aaee-bb7d538682bf/">Настройка репликации через код</a>
 * <p>
 * cluster/zookeeper/docker-compose.yml
 * </p>
 */
public class Replication {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9081");
        /**
         * Определяет уровень подтверждений от брокеров. Значение all требует,
         * чтобы все реплики синхронно подтвердили получение сообщения.
         * Только после этого сообщение считается успешно отправленным.
         */
        props.put(ProducerConfig.ACKS_CONFIG, "all"); // Для синхронной репликации

        /**
         * Задаёт количество повторных попыток при отправке сообщений, если возникает ошибка ― например,
         * если брокер временно недоступен.
         */
        props.put(ProducerConfig.RETRIES_CONFIG, 3);

        /**
         * Указывает минимальное число реплик, которые должны быть в синхронном состоянии
         * и подтвердить получение сообщения для выполнения успешной записи.
         */
        props.put(TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG, "2"); // Минимум 2 реплики должны подтвердить запись
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

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
