package dev.TrueFood.dto;

import dev.TrueFood.entity.Category;
import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.users.OrderType;
import dev.TrueFood.entity.users.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TaskDto {

    private Long id;

    private String title;

    private String description;

    private Category category;

    private Long categoryId;

    private int price;

    private Image imagesId;

    private String location;

    private User Author;

    private Long authorId;

    private OrderType orderType;

    private Date createdAt;

    private boolean enable;

    private List<User> workers = new ArrayList<>();

}
