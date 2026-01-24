package dev.TrueFood.services;

import dev.TrueFood.dto.RatingUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RatingUpdateProducer {

    private final KafkaTemplate<String, RatingUpdateEvent> kafkaTemplate;

    public void sendRatingUpdate(RatingUpdateEvent event) {
        try {
            kafkaTemplate.send("rating-updates", event);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send rating update event", e);
        }
    }
}