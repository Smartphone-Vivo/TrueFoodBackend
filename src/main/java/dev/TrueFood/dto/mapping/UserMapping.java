package dev.TrueFood.dto.mapping;

import dev.TrueFood.dto.SignUpRequest;
import dev.TrueFood.entity.users.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapping {

    User toEntity(SignUpRequest signUpRequest);
}
