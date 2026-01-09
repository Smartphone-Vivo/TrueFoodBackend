package dev.TrueFood.services;

import dev.TrueFood.dto.SignInRequest;
import dev.TrueFood.entity.BaseUser;
import dev.TrueFood.exceptions.NotFoundException;
import dev.TrueFood.jwt.JwtProvider;
import dev.TrueFood.jwt.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BaseUserService baseUserService;
    private final JwtProvider jwtProvider;

    public JwtResponse login(SignInRequest signInRequest) {
        final BaseUser baseUser = baseUserService.getByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("user not found"));

        String encodedPassword = baseUser.getPassword().getPassword();

        String signInRequestPassword = signInRequest.getPassword();

        if(Objects.equals(encodedPassword, signInRequestPassword)){
            final String token = jwtProvider.generateAccessToken(baseUser);
            return new JwtResponse(token);
        }else{
            throw new NotFoundException("wrong password");
        }

    }


}
