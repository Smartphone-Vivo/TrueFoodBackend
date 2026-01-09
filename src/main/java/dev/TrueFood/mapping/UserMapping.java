package dev.TrueFood.mapping;

import dev.TrueFood.dto.UserDto;
import dev.TrueFood.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapping {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "enable", target = "enable")
    @Mapping(source = "fio", target = "fio")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "favourites", target = "favourites")
    @Mapping(source = "reviews", target = "reviews")
    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}