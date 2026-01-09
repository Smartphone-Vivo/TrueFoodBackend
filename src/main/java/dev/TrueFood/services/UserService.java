package dev.TrueFood.services;

import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.dto.UserDto;
import dev.TrueFood.exceptions.NotFoundException;
import dev.TrueFood.mapping.AdvertisementMapping;
import dev.TrueFood.mapping.UserMapping;
import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Review;
import dev.TrueFood.entity.User;
import dev.TrueFood.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final AdvertisementRepository advertisementRepository;
    private final ReviewRepository reviewRepository;
    private final AdvertisementMapping advertisementMapping;
    private final UserMapping userMapping;

    public UserDto getMyProfile(Long id){
        return userRepository.findById(id).map(userMapping::toDto).orElse(null);
    }

    public UserDto getProfile(Long id){
        User user = userRepository.findByUserId(id);

        return userMapping.toDto(user);
    }

    public void addReview(Review review, Long id, Long userId){
        if(Objects.equals(id, userId)){
            throw new RuntimeException("самолайк отклонен("); //todo сделать кастомное
        }
        else{
            User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));

            int rating = 0;

            for(Review rev : user.getReviews()){
                rating += rev.getRating();
            }

            rating += review.getRating();

            if(user.getReviews().isEmpty()){
                user.setRating(review.getRating());
            }
            else{
                user.setRating(rating / (user.getReviews().size() + 1));
            }

            reviewRepository.save(review);
            List<Review> userReviews = user.getReviews();
            userReviews.add(review);
            user.setReviews(userReviews);
            userRepository.save(user);
        }
    }
}