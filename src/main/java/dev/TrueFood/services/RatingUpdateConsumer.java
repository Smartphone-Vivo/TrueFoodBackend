package dev.TrueFood.services;

import dev.TrueFood.dto.RatingUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingUpdateConsumer {

    private final ReviewService reviewService;

    @KafkaListener(topics = "rating-updates", groupId = "truefood-rating-group")
    public void consumeRatingUpdate(RatingUpdateEvent event){
        reviewService.updateRatingFromKafka(event);
    }

}
