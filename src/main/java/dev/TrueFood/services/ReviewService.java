package dev.TrueFood.services;

import dev.TrueFood.dto.RatingUpdateEvent;
import dev.TrueFood.dto.ReviewDto;
import dev.TrueFood.entity.Review;
import dev.TrueFood.entity.User;
import dev.TrueFood.exceptions.FailedUploadException;
import dev.TrueFood.exceptions.SelfLikeException;
import dev.TrueFood.mapping.ReviewMapping;
import dev.TrueFood.repositories.ReviewRepository;
import dev.TrueFood.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapping reviewMapping;
    private final RatingUpdateProducer ratingUpdateProducer;

    @Transactional
    public void addReview(ReviewDto reviewDto, Long authorId, Long targetUserId){
        if(Objects.equals(authorId, targetUserId)) {
            throw new SelfLikeException("самолайк отклонен(");
        }

        if(reviewDto.getRating() < 1 || reviewDto.getRating() > 5) {
            throw new RuntimeException("Rating must 1 > 5");
        }

        Review review = reviewMapping.toEntity(reviewDto);

        User targetUser = userRepository.getReferenceById(targetUserId);

        review.setTargetUser(targetUser);

        reviewRepository.save(review);

        RatingUpdateEvent event = RatingUpdateEvent.builder()
                .targetUserId(targetUserId)
                .rating(reviewDto.getRating())
                .build();

        ratingUpdateProducer.sendRatingUpdate(event);
    }

    @Transactional
    public void updateRatingFromKafka(RatingUpdateEvent event){
        try{
            userRepository.updateUserRating(event.getTargetUserId());
        }
        catch(Exception e){
            throw new FailedUploadException("failed to update user rating");
        }
    }

}
