package dev.TrueFood.services;

import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.dto.UserDto;
import dev.TrueFood.dto.mapping.AdvertisementMapping;
import dev.TrueFood.dto.mapping.UserMapping;
import dev.TrueFood.entity.Order;
import dev.TrueFood.entity.Review;
import dev.TrueFood.entity.users.User;
import dev.TrueFood.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final AdvertisementRepository advertisementRepository;
    private final ImageRepository imageRepository;
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final AdvertisementMapping advertisementMapping;
    private final UserMapping userMapping;

    public UserDto getMyProfile(Long id){
        return userRepository.findById(id).map(userMapping::toDto).orElse(null);
    }

    public UserDto getProfile(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));

        UserDto userDto = userMapping.toDto(user);

        return userDto;
    }

    public Page<AdvertisementDto> getAdvertisementsByUser(Long id, PageRequest pageRequest){
        return advertisementRepository.getAdverticementByUser(id, pageRequest).map(advertisementMapping::toDto);
    }

    public void addToFavourites(Long id, Long advId){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        Order order = orderRepository.findById(advId).orElseThrow(() -> new RuntimeException("advertisement not found"));

        List<Order> userFavourites = user.getFavourites();

        if(userFavourites.contains(order)){
            throw new RuntimeException("advertisement is already in favourite");
        }
        else{
            userFavourites.add(order);

            user.setFavourites(userFavourites);

            userRepository.save(user);
        }
    }

    public void addReview(Review review, Long id, Long userId){
        if(Objects.equals(id, userId)){
            throw new RuntimeException("самолайк отклонен(");
        }
        else{
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
            reviewRepository.save(review);
            List<Review> userReviews = user.getReviews();
            userReviews.add(review);
            user.setReviews(userReviews);
            userRepository.save(user);
        }
    }
}