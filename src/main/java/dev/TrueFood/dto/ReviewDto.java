package dev.TrueFood.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {

    private Long id;

    private Long authorId;

    private int rating;

    private String reviewText;
    
}
