package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response;

import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Classes;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Room;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Shift;
import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.DayOfWeek;
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
    ClassesResponseNoList classes;
    RoomResponse room;
    ShiftResponseNoList shift;
    DayOfWeek dayOfWeek;
}

