package dev.TrueFood.controllers;

import dev.TrueFood.dto.SignInRequest;
import dev.TrueFood.dto.SignUpRequest;
import dev.TrueFood.jwt.JwtResponse;
import dev.TrueFood.services.AuthService;
import dev.TrueFood.services.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RegisterService registerService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody SignInRequest signInRequest){
        final JwtResponse token = authService.login(signInRequest);

        return ResponseEntity.ok(token);
    }

    @PostMapping("register")
    public void register(@RequestBody SignUpRequest signUpRequest){
        registerService.register(signUpRequest);

    }

}
