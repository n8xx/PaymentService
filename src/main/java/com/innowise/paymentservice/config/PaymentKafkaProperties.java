package com.innowise.paymentservice.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Settings of the payment events topic.
 *
 * @param topic name of the topic payment events are published to
 * @param partitions number of partitions of the topic
 * @param replicas replication factor of the topic
 */
@Validated
@ConfigurationProperties(prefix = "payment.kafka")
public record PaymentKafkaProperties(
        @NotBlank String topic, @Positive int partitions, @Positive short replicas) {
}
