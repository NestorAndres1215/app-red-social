package com.red_social.auth_service.kafka;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private static final String TOPIC = "user-events-topic";

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    public void publishEvent(UserEvent event) {
        kafkaTemplate.send(TOPIC, event.getUserId(), event);
        System.out.println("📤 Evento enviado a Kafka -> " + event);
    }
}
