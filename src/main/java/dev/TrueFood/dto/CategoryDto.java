package dev.TrueFood.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

    private Long id;

    private CategoryDto parent;

    private String name;

}
