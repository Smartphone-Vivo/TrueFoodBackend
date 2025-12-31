package dev.TrueFood.services;

import dev.TrueFood.dto.SignUpRequest;
import dev.TrueFood.dto.mapping.UserMapping;
import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.users.Password;
import dev.TrueFood.entity.users.Role;
import dev.TrueFood.entity.users.User;
import dev.TrueFood.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class RegisterService {

//    private final UserMapping userMapping;
    private final UserRepository userRepository;

    public void register(SignUpRequest signUpRequest) {

        Password password = new Password(null, signUpRequest.getPassword());

        Image image = new Image();



        //todo Image это лист надо подумать над этим в следующем году)))
        User user = new User(
                null,
                signUpRequest.getEmail(),
                signUpRequest.getAvatar(),
                Role.USER,
                password,
                true,
                signUpRequest.getFio(),
                5
        );

//        User user = userMapping.toEntity(signUpRequest);

        userRepository.save(user);
    }
}