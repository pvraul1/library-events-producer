package com.learnkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * AutoCreateConfig
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 22/02/2025 - 21:32
 * @since 1.17
 */
@Configuration
public class AutoCreateConfig {

    @Value("${spring.kafka.topic:library-events}")
    private String topic;

    @Bean
    public NewTopic libraryEvents() {
        return TopicBuilder.name(topic)
                .partitions(3)
                .replicas(3)
                .build();
    }

}
