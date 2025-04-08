package com.ddd.scheduleservice.Dto.Response;

import com.ddd.scheduleservice.Enum.DayOfWeek;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassSchedulesResponse {
    Integer scheduleId;
    String subjectName;
    String userName;
    LocalTime startTime;
    LocalDate dateStart;
    LocalTime endTime;
    String roomName;
    String location;
    DayOfWeek dayOfWeek;
}

