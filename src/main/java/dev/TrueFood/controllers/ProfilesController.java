package dev.TrueFood.controllers;

import dev.TrueFood.dto.UserDto;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/profile")
@RequiredArgsConstructor
public class ProfilesController {

    private final UserService userService;

    @GetMapping("{id}")
    public UserDto getProfile(@PathVariable Long id) {
        return userService.getProfile(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("my-profile")
    public UserDto getMyProfile(JwtAuthentication authentication) {
        Long id = authentication.getUserId();
        return userService.getMyProfile(id);
    }
}
