package com.example.serviceteacschedule_and_makeup_schedule_service.Mapper;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.DangkybuRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.DangkybuResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Dangkybu;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.DangkybuUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DangkybuMapper {
    @Mapping(target = "giangvien",ignore = true)
    @Mapping(target = "phongmay", ignore = true)
    @Mapping(target = "monhoc", ignore = true)
    @Mapping(target = "lop", ignore = true)
    Dangkybu toDangkybu(DangkybuRequest request);
    DangkybuResponse toDangkybuResponse(Dangkybu entity);
    void updateDangkybu(@MappingTarget Dangkybu Entity, DangkybuUpdate update);
}
