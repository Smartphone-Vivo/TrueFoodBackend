package dev.TrueFood.controllers;

import dev.TrueFood.dto.ReviewDto;
import dev.TrueFood.entity.Review;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/reviews")
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("add-review/{userId}")
    public void addReview(
            @NotNull @Positive @PathVariable(name = "userId") Long userId,
            @Valid @RequestBody ReviewDto reviewDto,
            JwtAuthentication authentication){
        Long authorId = authentication.getUserId();
        userService.addReview(reviewDto, authorId, userId);
    }

}
