package dev.TrueFood.services;

import dev.TrueFood.dto.SignUpRequest;
import dev.TrueFood.dto.mapping.UserMapping;
import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.users.Password;
import dev.TrueFood.entity.users.Role;
import dev.TrueFood.entity.users.User;
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

        String avatar = signUpRequest.getImageUrl();

        images.add(avatar);

        Image image = new Image(
                null,
                images
        );

        imageRepository.save(image);

        //todo Image это лист надо подумать над этим в следующем году)))
        User user = new User(
                null,
                signUpRequest.getEmail(),
                image,
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