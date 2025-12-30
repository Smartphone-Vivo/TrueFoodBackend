package dev.TrueFood.services;

import dev.TrueFood.dto.SignInRequest;
import dev.TrueFood.entity.users.BaseUser;
import dev.TrueFood.jwt.JwtProvider;
import dev.TrueFood.jwt.JwtResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BaseUserService baseUserService;
    private final UserDetailsPasswordService userDetailsPasswordService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtProvider jwtProvider;

    public JwtResponse login(@NonNull SignInRequest signInRequest) {
        final BaseUser baseUser = baseUserService.getByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String encodedPassword = baseUser.getPassword().getPassword();

        String signInRequestPassword = signInRequest.getPassword();

//        if(passwordEncoder.matches(encodedPassword, signInRequestPassword)){
        if(Objects.equals(encodedPassword, signInRequestPassword)){
            final String token = jwtProvider.generateAccessToken(baseUser);
            return new JwtResponse(token);
        }else{
            throw new RuntimeException("Wrong password");
        }

    }


}
