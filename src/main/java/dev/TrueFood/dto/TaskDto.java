package dev.TrueFood.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TaskDto {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String title;

    @NotBlank
    @Size(min = 1, max = 100)
    private String description;

    @NotNull
    private Long categoryId;

    @NotNull
    @Positive
    private int price;

    @NotNull
    private ImageDto images;

    @NotNull
    private String location;

    @NotNull
    private Long authorId;

    private LocalDateTime createdAt;

    private UserDto acceptedWorker;

    private List<UserDto> workers;

    private boolean enable;

}
