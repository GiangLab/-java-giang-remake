package com.example.KurGiangremake.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public static final String TASK_TOPIC = "tasks";

    @Bean
    public NewTopic taskTopic() {
        return new NewTopic(TASK_TOPIC, 1, (short) 1);
    }
}
