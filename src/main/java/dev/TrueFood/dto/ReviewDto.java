package dev.TrueFood.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {

    @Null
    private Long id;

    @NotNull
    private Long authorId;

    @NotNull
    @Positive
    private int rating;

    @NotBlank
    private String reviewText;
    
}
