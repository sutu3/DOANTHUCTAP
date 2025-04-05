package com.ddd.scheduleservice.Mapper;

import com.ddd.scheduleservice.Dto.Request.ClassesRequest;
import com.ddd.scheduleservice.Dto.Response.ClassesResponse;
import com.ddd.scheduleservice.Entity.Classes;
import com.ddd.scheduleservice.Form.ClassesUpdate;
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
