package com.example.serviceteacschedule_and_makeup_schedule_service.Mapper;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.LichgiangdayRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.LichgiangdayResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Lichgiangday;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.LichgiangdayUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LichgiangdayMapper {
    @Mapping(target = "giangvien",ignore = true)
    @Mapping(target = "phongmay", ignore = true)
    @Mapping(target = "monhoc", ignore = true)
    @Mapping(target = "lop", ignore = true)
    Lichgiangday toLichgiangday(LichgiangdayRequest request);
    LichgiangdayResponse toLichgiangdayResponse(Lichgiangday entity);
    void updateLichgiangday(@MappingTarget Lichgiangday Entity, LichgiangdayUpdate update);
}
