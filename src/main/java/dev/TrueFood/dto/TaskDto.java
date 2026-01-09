package dev.TrueFood.dto;

import dev.TrueFood.entity.Image;
import dev.TrueFood.enums.OrderType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TaskDto {

    private Long id;

    private String title;

    private String description;

    private Long categoryId;

    private int price;

    private Image imagesId;

    private String location;

    private Long authorId;

    private OrderType orderType;

    private Date createdAt;

    private boolean enable;

    private UserDto acceptedWorker;

    private List<UserDto> workers;

}
