package com.example.serviceteacschedule_and_makeup_schedule_service.Mapper;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.ClassesRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ClassesResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Classes;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.ClassesUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClassesMapper {
    Classes toClasses(ClassesRequest request);
    ClassesResponse toClassesResponse(Classes Classes);
    @Mapping(target = "subject",ignore = true)
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "room",ignore = true)
    @Mapping(target = "shift",ignore = true)
    void updateClasses(@MappingTarget Classes Classes, ClassesUpdate update);
}
