package com.ddd.scheduleservice.Mapper;

import com.ddd.scheduleservice.Dto.Request.UserRequest;
import com.ddd.scheduleservice.Dto.Response.UserResponse;
import com.ddd.scheduleservice.Entity.User;
import com.ddd.scheduleservice.Form.UserUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);
    UserResponse toUserResponse(User User);
    void updateUser(@MappingTarget User User, UserUpdate update);
}
