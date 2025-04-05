package com.ddd.scheduleservice.Dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShiftResponseNoList {
    int shiftId;
    String shiftName;
    /*List<ClassSchedules> classesSchedules;
    List<Classes> classes;*/
    LocalTime startTime;
    LocalTime endTime;
}
