package dev.TrueFood.mapping;

import dev.TrueFood.dto.ImageDto;
import dev.TrueFood.entity.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapping {

    ImageDto toDto(Image image);

    Image toEntity(ImageDto imageDto);

}
