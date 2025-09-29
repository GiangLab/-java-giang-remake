package com.example.KurGiangremake.messaging;

import com.example.KurGiangremake.domain.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TaskProducer {

    private static final Logger logger = LoggerFactory.getLogger(TaskProducer.class);

    private static final String TOPIC = "task-created";

    private final KafkaTemplate<String, Task> kafkaTemplate;

    public TaskProducer(KafkaTemplate<String, Task> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Gửi message khi có task mới được tạo
     */
    public void sendTaskCreated(Task task) {
        logger.info("Publishing Task Created event to Kafka: {}", task);
        kafkaTemplate.send(TOPIC, String.valueOf(task.getId()), task);
    }
}
