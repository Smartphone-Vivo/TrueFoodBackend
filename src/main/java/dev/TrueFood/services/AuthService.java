package dev.TrueFood.services;

import dev.TrueFood.dto.SignInRequest;
import dev.TrueFood.entity.BaseUser;
import dev.TrueFood.exceptions.NotFoundException;
import dev.TrueFood.jwt.JwtProvider;
import dev.TrueFood.jwt.JwtResponse;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BaseUserService baseUserService;
    private final JwtProvider jwtProvider;

    private final Map<String, String> refreshStorage = new ConcurrentHashMap<>();


    public JwtResponse login(SignInRequest signInRequest) {
        final BaseUser baseUser = baseUserService.getByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("user not found"));

        String encodedPassword = baseUser.getPassword().getPassword();

        String signInRequestPassword = signInRequest.getPassword();

        if(Objects.equals(encodedPassword, signInRequestPassword) && baseUser.isEnable()){
            final String accessToken = jwtProvider.generateAccessToken(baseUser);
            final String refreshToken = jwtProvider.generateRefreshToken(baseUser);
            refreshStorage.put(baseUser.getEmail(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        }else{
            throw new NotFoundException("wrong password");
        }

    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final BaseUser user = baseUserService.getByEmail(login)
                        .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getEmail(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        return new JwtResponse(null, null);
    }

//    public JwtResponse refresh(@NonNull String refreshToken) {
//        if (jwtProvider.validateRefreshToken(refreshToken)) {
//            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
//            final String login = claims.getSubject();
//            final String saveRefreshToken = refreshStorage.get(login);
//            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
//                final BaseUser user = baseUserService.getByEmail(login)
//                        .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
//                final String accessToken = jwtProvider.generateAccessToken(user);
//                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
//                refreshStorage.put(user.getEmail(), newRefreshToken);
//                return new JwtResponse(accessToken, newRefreshToken);
//            }
//        }
//        throw new BadCredentialsException("Невалидный JWT токен");
//    }


}
