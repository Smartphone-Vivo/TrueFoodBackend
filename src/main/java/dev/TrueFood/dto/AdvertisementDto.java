package dev.TrueFood.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementDto {

    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String title;

    @NotNull
    @Size(min = 3, max = 150)
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

    private boolean enable;

}
