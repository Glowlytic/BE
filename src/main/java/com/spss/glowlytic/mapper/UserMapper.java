package com.spss.glowlytic.mapper;

import com.spss.glowlytic.dto.request.auth.CreateUserRequest;
import com.spss.glowlytic.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "status", ignore = true)
//    @Mapping(target = "id", ignore = true)
    User toEntity(CreateUserRequest request);
}