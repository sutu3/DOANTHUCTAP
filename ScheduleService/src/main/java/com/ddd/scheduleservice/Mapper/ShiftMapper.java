package com.ddd.scheduleservice.Mapper;

import com.ddd.scheduleservice.Dto.Request.ShiftRequest;
import com.ddd.scheduleservice.Dto.Response.ShiftResponse;
import com.ddd.scheduleservice.Entity.Shift;
import com.ddd.scheduleservice.Form.ShiftUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShiftMapper {
    Shift toShift(ShiftRequest request);
    ShiftResponse toShiftResponse(Shift Shift);
    void updateShift(@MappingTarget Shift Shift, ShiftUpdate update);
}
