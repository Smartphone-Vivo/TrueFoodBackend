package dev.TrueFood.services;

import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.dto.ContactsDto;
import dev.TrueFood.dto.ReviewDto;
import dev.TrueFood.dto.UserDto;
import dev.TrueFood.exceptions.NotFoundException;
import dev.TrueFood.mapping.ReviewMapping;
import dev.TrueFood.mapping.UserMapping;
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
    private final ReviewRepository reviewRepository;
    private final UserMapping userMapping;
    private final ReviewMapping reviewMapping;

    public UserDto getMyProfile(Long id){
        return userRepository.findById(id).map(userMapping::toDto).orElse(null);
    }

    public UserDto getProfile(Long id){
        User user = userRepository.findByUserId(id);

        return userMapping.toDto(user);
    }

    public void addReview(ReviewDto reviewDto, Long id, Long userId){
        if(Objects.equals(id, userId)){
            throw new RuntimeException("самолайк отклонен(");
        }
        else{
            User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));

            int rating = 0;

            //todo native sql update rating
            //todo look at n+1
            for(Review rev : user.getReviews()){
                rating += rev.getRating();
            }

            rating += reviewDto.getRating();

            if(user.getReviews().isEmpty()){
                user.setRating(reviewDto.getRating());
            }
            else{
                user.setRating(rating / (user.getReviews().size() + 1));
            }

            Review review = reviewMapping.toEntity(reviewDto);

            reviewRepository.save(review);

            List<Review> userReviews = user.getReviews();
            userReviews.add(review);
            user.setReviews(userReviews);
            userRepository.save(user);
        }
    }

    public ContactsDto getUserContacts(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
        return new ContactsDto(user.getContacts());
    }

    public Page<UserDto> getAllUsers(String name, PageRequest pageRequest){
        return userRepository.getAllUsers(name, pageRequest).map(userMapping::toDto);
    }

    public void banControlUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));

        user.setEnable(!(user.isEnable()));

        userRepository.save(user);
    }
}