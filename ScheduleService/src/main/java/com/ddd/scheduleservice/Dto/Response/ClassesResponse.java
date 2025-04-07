package com.ddd.scheduleservice.Dto.Response;

import com.ddd.scheduleservice.Entity.*;
import com.ddd.scheduleservice.Enum.ClassStatus;
import com.ddd.scheduleservice.Enum.ClassType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassesResponse {
    int classId;
    SubjectResponseNoList subject;
    /*RoomResponse room;*/
    UserResponseNoList user;
    /*ShiftResponseNoList shift;*/
/*
    List<ClassSchedulesResponseNoClass> classesSchedules;
*/
    LocalDate startTime;
    LocalDate endTime;
    ClassType type;
    ClassStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

