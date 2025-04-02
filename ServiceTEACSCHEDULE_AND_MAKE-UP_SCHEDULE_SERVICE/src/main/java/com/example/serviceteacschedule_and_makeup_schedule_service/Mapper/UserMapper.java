package com.example.serviceteacschedule_and_makeup_schedule_service.Mapper;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.UserRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.UserResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.User;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.UserUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);
    UserResponse toUserResponse(User User);
    void updateUser(@MappingTarget User User, UserUpdate update);
}
