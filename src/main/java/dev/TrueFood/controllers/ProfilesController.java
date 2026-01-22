package dev.TrueFood.controllers;

import dev.TrueFood.dto.ContactsDto;
import dev.TrueFood.dto.UserDto;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.services.UserService;
import dev.TrueFood.utils.PageUtils;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/profile")
@RequiredArgsConstructor
@Validated
public class ProfilesController {

    private final UserService userService;

    @GetMapping("{id}")
    public UserDto getProfile(
            @NotNull
            @Positive
            @PathVariable Long id) {
        return userService.getProfile(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("my-profile")
    public UserDto getMyProfile(JwtAuthentication authentication) {
        Long id = authentication.getUserId();
        return userService.getMyProfile(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("user-contacts/{id}")
    public ContactsDto getUserContacts(
            @NotNull
            @Positive
            @PathVariable Long id) {
        return userService.getUserContacts(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("get-all-users/{page}/{size}")
    public Page<UserDto> getAllUsers(

            @Positive @PathVariable(name = "page") int page,

            @Positive @PathVariable(name = "size")int size,

            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(name = "sort", defaultValue = "id,asc") String sort) {

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, sort);

        return userService.getAllUsers(name, pageRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("ban-user/{id}")
    public void banControlUser(@NotNull @Positive @PathVariable Long id) {
        userService.banControlUser(id);
    }
}