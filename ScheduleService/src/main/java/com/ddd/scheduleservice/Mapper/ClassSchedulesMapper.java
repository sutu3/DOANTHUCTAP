package com.ddd.scheduleservice.Mapper;

import com.ddd.scheduleservice.Dto.Request.ClassSchedulesRequest;
import com.ddd.scheduleservice.Dto.Response.ClassSchedulesResponse;
import com.ddd.scheduleservice.Entity.ClassSchedules;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClassSchedulesMapper {
    @Mapping(target = "classes",ignore = true)
    @Mapping(target = "room",ignore = true)
    @Mapping(target = "shift",ignore = true)
    @Mapping(target = "dayOfWeek",ignore = true)
    ClassSchedules toClassSchedules(ClassSchedulesRequest request);
    @Mapping(source = "classes.subject.subjectName", target = "subjectName")
    @Mapping(source = "classes.user.username", target = "userName")
    @Mapping(source = "classes.shift.startTime", target = "startTime")
    @Mapping(source = "classes.shift.endTime", target = "endTime")
    @Mapping(source = "classes.room.roomName", target = "roomName")
    @Mapping(source = "classes.room.location", target = "location")
    ClassSchedulesResponse toClassSchedulesResponse(ClassSchedules ClassSchedules);
    /*@Mapping(target = "subject",ignore = true)
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "room",ignore = true)
    @Mapping(target = "shift",ignore = true)*/
/*
    void updateClassSchedules(@MappingTarget ClassSchedules ClassSchedules, ClassSchedulesUpdate update);
*/
}
