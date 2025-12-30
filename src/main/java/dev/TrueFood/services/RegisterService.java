package dev.TrueFood.services;

import dev.TrueFood.dto.SignUpRequest;
import dev.TrueFood.dto.mapping.UserMapping;
import dev.TrueFood.entity.users.Password;
import dev.TrueFood.entity.users.Role;
import dev.TrueFood.entity.users.User;
import dev.TrueFood.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class RegisterService {

//    private final UserMapping userMapping;
    private final UserRepository userRepository;

    public void register(SignUpRequest signUpRequest) {

        Password password = new Password(null, signUpRequest.getPassword());

        User user = new User(
                null,
                signUpRequest.getEmail(),
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