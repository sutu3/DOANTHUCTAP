package com.example.serviceteacschedule_and_makeup_schedule_service.Mapper;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.PhongmayRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.PhongmayResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Phongmay;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.PhongmayUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PhongmayMapper {
    Phongmay toPhongmay(PhongmayRequest request);
    PhongmayResponse toPhongmayResponse(Phongmay entity);
    void updatePhongmay(@MappingTarget Phongmay Entity, PhongmayUpdate update);
}
