package com.innowise.paymentservice.kafka;

import com.innowise.paymentservice.config.PaymentKafkaProperties;
import com.innowise.paymentservice.dto.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Publishes payment events to Kafka.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventPublisher {

    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    private final PaymentKafkaProperties properties;

    /**
     * Publishes the event keyed by order identifier, so that all events of one order go to the
     * same partition and keep their order.
     *
     * @param event event to publish
     */
    public void publish(PaymentEvent event) {
        String key = String.valueOf(event.orderId());
        kafkaTemplate.send(properties.topic(), key, event)
                .whenComplete((result, e) -> {
                    if (e != null) {
                        log.error("Failed to publish payment event: paymentId={}, orderId={}",
                                event.paymentId(), event.orderId(), e);
                    } else {
                        log.info("Published payment event: paymentId={}, orderId={}, status={}",
                                event.paymentId(), event.orderId(), event.status());
                    }
                });
    }
}
