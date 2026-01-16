package dev.TrueFood.mapping;

import dev.TrueFood.dto.ReviewDto;
import dev.TrueFood.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapping {

    Review toEntity(ReviewDto reviewDto);

}
