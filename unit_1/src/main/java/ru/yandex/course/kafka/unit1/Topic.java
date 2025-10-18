package ru.yandex.course.kafka.unit1;

import java.util.Collections;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.NewTopic;

/**
 * <a href="https://practicum.yandex.ru/learn/kafka/courses/83ca50bc-5dce-42cd-b629-997c971f1765/sprints/713562/topics/cfde3128-d6eb-4b4e-ba60-a0999d162a79/lessons/d41fce9c-73cd-4b74-bade-b3765cf9b984/">Создание и конфигурирование топиков</>
 * <p>
 * cluster/zookeeper/docker-compose.yml
 * </p>
 *
 * @see <a href=https://github.com/provectus/kafka-ui/issues/4089>Not all brokers are shown in the brokers page </a>
 */
public class Topic {

    public static void main(String[] args) {
        try (Admin admin = Admin.create(Collections.singletonMap("bootstrap.servers", "localhost:9082"))) {
            NewTopic newTopic = new NewTopic("passage32", 1, (short) 1);
            admin.createTopics(Collections.singleton(newTopic)).all().get();
            System.out.println("Topic 'passage1' created successfully!");

        } catch (ExecutionException | InterruptedException e) {
            System.err.println("=======> " + e.getMessage());
        }

    }
}