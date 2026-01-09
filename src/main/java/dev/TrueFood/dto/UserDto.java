package dev.TrueFood.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.Review;
import dev.TrueFood.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private Long id;

    private String email;

    private Image avatar;

    private Role role;

    private boolean enable;

    private String fio;

    private int rating;

    @JsonIgnore
    private List<AdvertisementDto> favourites;

    private List<Review> reviews;

}