package dev.TrueFood.dto;

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

    private ImageDto images;

    private String location;

    private Long authorId;

    private Date createdAt;

    private boolean enable;

    private UserDto acceptedWorker;

    private List<UserDto> workers;

}
