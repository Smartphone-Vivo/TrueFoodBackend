package dev.TrueFood.dto;
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

    private Long categoryId;

    private int price;

    private ImageDto imagesId;

    private String location;

    private Long authorId;

    private Date createdAt;

    private boolean enable;

}
