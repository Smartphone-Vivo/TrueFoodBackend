package dev.TrueFood.mapping;

import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.entity.Advertisement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdvertisementMapping {

//    @Mapping(target = "categoryId", expression = "java(advertisement.getCategory().getId())")
//    @Mapping(target = "authorId", expression = "java(advertisement.getAuthor().getId())")
    AdvertisementDto toDto(Advertisement advertisement);


    Advertisement toEntity(AdvertisementDto advertisementDto);

    @Mapping(target = "createdAt", ignore = true)
    Advertisement updateAdvertisement(AdvertisementDto advertisementDto, @MappingTarget Advertisement advertisement);

}