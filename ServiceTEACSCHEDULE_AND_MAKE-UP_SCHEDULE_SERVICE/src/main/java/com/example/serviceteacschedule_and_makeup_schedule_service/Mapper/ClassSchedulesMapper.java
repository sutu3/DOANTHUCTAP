package com.example.serviceteacschedule_and_makeup_schedule_service.Mapper;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.ClassSchedulesRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ClassSchedulesResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.ClassSchedules;
/*import com.example.serviceteacschedule_and_makeup_schedule_service.Form.ClassSchedulesUpdate;*/
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClassSchedulesMapper {
    @Mapping(target = "classes",ignore = true)
    @Mapping(target = "room",ignore = true)
    @Mapping(target = "shift",ignore = true)
    @Mapping(target = "dayOfWeek",ignore = true)
    ClassSchedules toClassSchedules(ClassSchedulesRequest request);
    ClassSchedulesResponse toClassSchedulesResponse(ClassSchedules ClassSchedules);
    /*@Mapping(target = "subject",ignore = true)
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "room",ignore = true)
    @Mapping(target = "shift",ignore = true)*/
/*
    void updateClassSchedules(@MappingTarget ClassSchedules ClassSchedules, ClassSchedulesUpdate update);
*/
}
