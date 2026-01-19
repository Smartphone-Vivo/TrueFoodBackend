package dev.TrueFood.services;

import dev.TrueFood.dto.SignUpRequest;
import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.Password;
import dev.TrueFood.enums.Role;
import dev.TrueFood.entity.User;
import dev.TrueFood.exceptions.EmailIsAlreadyUse;
import dev.TrueFood.repositories.ImageRepository;
import dev.TrueFood.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Transactional
    public void register(SignUpRequest signUpRequest) {

        if(userRepository.existsByEmail(signUpRequest.getEmail())){
            throw new EmailIsAlreadyUse("Этот email уже кем то занят");
        }

        User user = new User(
                null,
                signUpRequest.getEmail(),
                new Image(null, new ArrayList<>(List.of(signUpRequest.getImageUrl()))),
                Role.USER,
                new Password(null, signUpRequest.getPassword()),
                true,
                signUpRequest.getFio(),
                0,
                signUpRequest.getContacts()
        );

        userRepository.save(user);



    }
}