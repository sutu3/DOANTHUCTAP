package com.example.serviceteacschedule_and_makeup_schedule_service.Mapper;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.GiangvienRequet;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.LopRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.GiangvienResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.LopResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Giangvien;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Lop;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.GiangvienUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.LopUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LopMapper {
    Lop toLop(LopRequest request);
    LopResponse toLopResponse(Lop entity);
    void updateLop(@MappingTarget Lop Entity, LopUpdate update);
}
