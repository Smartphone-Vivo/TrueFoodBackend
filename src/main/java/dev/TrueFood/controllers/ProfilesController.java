package dev.TrueFood.controllers;

import dev.TrueFood.dto.ContactsDto;
import dev.TrueFood.dto.UserDto;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.services.UserService;
import dev.TrueFood.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("user-contacts/{id}")
    public ContactsDto getUserContacts(@PathVariable Long id) {
        return userService.getUserContacts(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("get-all-users/{page}/{size}")
    public Page<UserDto> getAllUsers(
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size,

            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(name = "sort", defaultValue = "id,asc") String sort) {

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, sort);

        return userService.getAllUsers(name, pageRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("ban-user/{id}")
    public void banControlUser(@PathVariable Long id) {
        userService.banControlUser(id);
    }
}