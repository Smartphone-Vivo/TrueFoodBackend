package dev.TrueFood.mapping;

import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.entity.Advertisement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdvertisementMapping {

    @Mapping(target = "categoryId", expression = "java(advertisement.getCategory().getId())")
    AdvertisementDto toDto(Advertisement advertisement);

    Advertisement toEntity(AdvertisementDto advertisementDto);

}