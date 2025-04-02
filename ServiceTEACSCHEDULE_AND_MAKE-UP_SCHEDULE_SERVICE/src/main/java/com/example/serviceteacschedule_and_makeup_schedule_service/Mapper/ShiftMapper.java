package com.example.serviceteacschedule_and_makeup_schedule_service.Mapper;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.ShiftRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ShiftResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Shift;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.ShiftUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShiftMapper {
    Shift toShift(ShiftRequest request);
    ShiftResponse toShiftResponse(Shift Shift);
    void updateShift(@MappingTarget Shift Shift, ShiftUpdate update);
}
