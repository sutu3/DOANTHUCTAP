package com.example.serviceteacschedule_and_makeup_schedule_service.Mapper;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.GiangvienRequet;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.GiangvienResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Giangvien;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.GiangvienUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GiangvienMapper {
    Giangvien toGiangvien(GiangvienRequet request);
    GiangvienResponse toGiangvienResponse(Giangvien entity);
    void updateGiangvien(@MappingTarget Giangvien Entity, GiangvienUpdate update);
}
