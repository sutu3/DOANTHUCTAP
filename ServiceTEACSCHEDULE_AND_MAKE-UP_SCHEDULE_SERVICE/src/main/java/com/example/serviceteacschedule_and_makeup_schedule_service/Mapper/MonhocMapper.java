package com.example.serviceteacschedule_and_makeup_schedule_service.Mapper;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.LopRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.MonhocRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.LopResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.MonhocResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Lop;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Monhoc;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.LopUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.MonhocUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MonhocMapper {
    Monhoc toMonhoc(MonhocRequest request);
    MonhocResponse toMonhocResponse(Monhoc entity);
    void updateMonhoc(@MappingTarget Monhoc Entity, MonhocUpdate update);
}
