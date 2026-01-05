package dev.TrueFood.dto.mapping;

import dev.TrueFood.dto.CategoryDto;
import dev.TrueFood.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapping {

    CategoryDto toDto(Category category);
}
