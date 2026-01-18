package dev.TrueFood.controllers;

import dev.TrueFood.dto.ReviewDto;
import dev.TrueFood.entity.Review;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("add-review/{userId}")
    public void addReview(
            @PathVariable(name = "userId") Long userId,
            @RequestBody ReviewDto reviewDto, //todo [готово] entity - dto
            JwtAuthentication authentication){
        Long authorId = authentication.getUserId();
        userService.addReview(reviewDto, authorId, userId);
    }

}
