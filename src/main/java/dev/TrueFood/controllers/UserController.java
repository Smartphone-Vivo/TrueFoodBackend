package dev.TrueFood.controllers;

import dev.TrueFood.entity.Adverticement;
import dev.TrueFood.entity.users.User;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.repositories.UserRepository;
import dev.TrueFood.services.UserService;
import dev.TrueFood.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("my-profile")
    public User getMyProfile(JwtAuthentication authentication) {
        Long id = authentication.getUserId();
        return userService.getMyProfile(id);
    }

    @GetMapping("advertisements-by-user/{id}/{page}/{size}")
    public Page<Adverticement> getAdvertisementsByUser(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size

    ) {

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, "id,asc");

        return userService.getAdvertisementsByUser(id, pageRequest);
    }

}
