package dev.TrueFood.services;

import dev.TrueFood.dto.SignUpRequest;
import dev.TrueFood.dto.mapping.UserMapping;
import dev.TrueFood.entity.users.User;
import dev.TrueFood.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserMapping userMapping;
    private final UserRepository userRepository;

    public void register(SignUpRequest signUpRequest) {


        User user = userMapping.toEntity(signUpRequest);

        userRepository.save(user);
    }
}