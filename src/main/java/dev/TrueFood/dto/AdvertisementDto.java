package dev.TrueFood.dto;
import dev.TrueFood.entity.Category;
import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.users.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementDto {

    private Long id;

    private String title;

    private String description;

    private Category category;

    private Long categoryId;

    private int price;

    private Image imagesId;

    private String location;

    private UserDto Author;

    private Long authorId;

    private OrderType orderType;

    private Date createdAt;

    private boolean enable;

}
