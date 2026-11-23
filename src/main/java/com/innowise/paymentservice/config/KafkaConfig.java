package com.innowise.paymentservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Kafka infrastructure of the service.
 */
@Configuration
public class KafkaConfig {

    /**
     * Declares the payment events topic so it is created on startup when missing.
     *
     * @param properties topic settings
     * @return topic definition
     */
    @Bean
    public NewTopic paymentEventsTopic(PaymentKafkaProperties properties) {
        return TopicBuilder.name(properties.topic())
                .partitions(properties.partitions())
                .replicas(properties.replicas())
                .build();
    }
}
