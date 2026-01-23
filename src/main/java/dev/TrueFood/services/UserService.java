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