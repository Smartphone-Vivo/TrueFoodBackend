package dev.TrueFood.dto;

import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.users.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private String title;
    private String description;
    private Long categoryId;
    private int price;
    private Image imagesId;
    private String location;
    private Long authorId;
    private String orderType;
    private String createdAt;
    private boolean enable;



}