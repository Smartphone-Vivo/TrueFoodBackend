package dev.TrueFood.services;

import dev.TrueFood.dto.ReviewDto;
import dev.TrueFood.entity.Review;
import dev.TrueFood.entity.User;
import dev.TrueFood.exceptions.SelfLikeException;
import dev.TrueFood.mapping.ReviewMapping;
import dev.TrueFood.mapping.UserMapping;
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

        userRepository.updateUserRating(targetUserId);


    }

}
