package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response;

import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.ClassStatus;
import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.ClassType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassesResponseNoList {
    int classId;
    SubjectResponseNoList subject;
    UserResponseNoList user;
    LocalTime startTime;
    LocalTime endTime;
    ClassType type;
    ClassStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

