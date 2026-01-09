package dev.TrueFood.services;

import dev.TrueFood.dto.SignUpRequest;
import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.Password;
import dev.TrueFood.enums.Role;
import dev.TrueFood.entity.User;
import dev.TrueFood.repositories.ImageRepository;
import dev.TrueFood.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {

//    private final UserMapping userMapping;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public void register(SignUpRequest signUpRequest) {

        Password password = new Password(null, signUpRequest.getPassword());

        List<String> images = new ArrayList<>();

        images.add(signUpRequest.getImageUrl());

        Image image = new Image(null, images);

        imageRepository.save(image);

        User user = new User(
                null,
                signUpRequest.getEmail(),
                image,
                Role.USER,
                password,
                true,
                signUpRequest.getFio(),
                5,
                signUpRequest.getContacts()
        );

        userRepository.save(user);
    }
}