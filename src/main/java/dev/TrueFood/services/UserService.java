package dev.TrueFood.services;

import dev.TrueFood.dto.ContactsDto;
import dev.TrueFood.dto.ReviewDto;
import dev.TrueFood.dto.UserDto;
import dev.TrueFood.exceptions.NotFoundException;
import dev.TrueFood.exceptions.SelfLikeException;
import dev.TrueFood.mapping.ReviewMapping;
import dev.TrueFood.mapping.UserMapping;
import dev.TrueFood.entity.Review;
import dev.TrueFood.entity.User;
import dev.TrueFood.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    @Transactional
    public void addReview(ReviewDto reviewDto, Long authorId, Long userId){
        if(Objects.equals(authorId, userId)) {
            throw new SelfLikeException("самолайк отклонен(");
        }

        //todo look at n+1
        //todo сделать по человечески
        if(reviewDto.getRating() < 1 || reviewDto.getRating() > 5) {
            throw new RuntimeException("Rating must 1 > 5");
        }

        Review review = reviewMapping.toEntity(reviewDto);

        User user = userRepository.getReferenceById(userId);

        review.setUser(user);

        reviewRepository.save(review);

        user.getReviews().add(review);

        userRepository.updateUserRating(userId);

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