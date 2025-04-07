package com.ddd.scheduleservice.Dto.Response;

import com.ddd.scheduleservice.Enum.DayOfWeek;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassSchedulesResponse {
    Integer scheduleId;
    ClassesResponseNoList classes;/*
    RoomResponse room;
    ShiftResponseNoList shift;*/
    DayOfWeek dayOfWeek;
}

