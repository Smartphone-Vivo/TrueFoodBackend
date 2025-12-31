package dev.TrueFood.dto.mapping;

import dev.TrueFood.entity.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdvertisementDto {

    private Long id;
    private String title;
    private Long authorId;
    private String description;
    private Long categoryId;
    private int price;
    private String location;
    private Image imagesId;
    private String itemType;
    private String createdAt;
    private boolean enabl;


}
