package dev.TrueFood.mapping;

import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.entity.Advertisement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdvertisementMapping {

    AdvertisementDto toDto(Advertisement advertisement);

    Advertisement toEntity(AdvertisementDto advertisementDto);

}