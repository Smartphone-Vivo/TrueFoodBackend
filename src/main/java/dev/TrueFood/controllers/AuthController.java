package dev.TrueFood.controllers;

import dev.TrueFood.dto.SignInRequest;
import dev.TrueFood.dto.SignUpRequest;
import dev.TrueFood.jwt.JwtResponse;
import dev.TrueFood.jwt.RefreshJwtRequest;
import dev.TrueFood.services.AuthService;
import dev.TrueFood.services.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RegisterService registerService;

    @PostMapping("login")
    public JwtResponse login(@RequestBody @Valid SignInRequest signInRequest){
        return authService.login(signInRequest);
    }

    @PostMapping("register")
    public void register(@RequestBody @Valid SignUpRequest signUpRequest){
        registerService.register(signUpRequest);

    }

    @PostMapping("token")
    public JwtResponse getNewAccessToken(@RequestBody @Valid RefreshJwtRequest request) {
        return authService.getAccessToken(request.getRefreshToken());
    }

//    @PostMapping("refresh")
//    public JwtResponse getNewRefreshToken(@RequestBody @Valid RefreshJwtRequest request) {
//        return authService.refresh(request.getRefreshToken());
//    }


}
