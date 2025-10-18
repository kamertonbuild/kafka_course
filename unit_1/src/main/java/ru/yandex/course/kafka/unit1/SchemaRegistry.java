package ru.yandex.course.kafka.unit1;

import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializerConfig;
import java.util.Properties;
import java.util.UUID;
import lombok.Data;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * <a href="https://practicum.yandex.ru/learn/kafka/courses/83ca50bc-5dce-42cd-b629-997c971f1765/sprints/713562/topics/cfde3128-d6eb-4b4e-ba60-a0999d162a79/lessons/e878fb5f-c49e-4669-890c-71816da4318d/">Сериализация и десериализация данных</a>
 * <p>
 * cluster/zookeeper/schema-registry/docker-compose.yml
 * </p>
 */
public class SchemaRegistry {

    @Data
    public static class Product {

        private Integer id;
        private String name;
    }

    public static void main(String[] args) throws Exception {
        // Настройки для подключения к Kafka и Schema Registry
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9094");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
            StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
            KafkaJsonSchemaSerializer.class.getName());
        props.put(KafkaJsonSchemaSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");


        // Создание Kafka Producer
        Producer<String, Product> producer = new KafkaProducer<>(props);


        // Создание JSON-сообщения
        Product jsonMessage = new Product();
        jsonMessage.setId(1);
        jsonMessage.setName("Product Name");


        // Отправка сообщения в Kafka
        ProducerRecord<String, Product> record = new ProducerRecord<>("your-topic",
            UUID.randomUUID().toString(),
            jsonMessage);
        producer.send(record).get();


        // Закрытие Producer
        producer.close();
    }
}
