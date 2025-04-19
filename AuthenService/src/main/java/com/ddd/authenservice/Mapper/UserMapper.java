package com.ddd.authenservice.Mapper;

import com.ddd.authenservice.Dto.Request.UserRequest;
import com.ddd.authenservice.Dto.Response.UserResponse;
import com.ddd.authenservice.Entity.User;
import com.ddd.authenservice.Form.User_Update;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles",ignore = true)
    User toUser(UserRequest request);
    @Mapping(source = "idUser", target = "id") // Ánh xạ idMaterial của entity với id của DTO
    UserResponse toUserResponse(User user);
    @Mapping(target = "roles",ignore = true)
    void updateUser(@MappingTarget User user, User_Update update);
}
