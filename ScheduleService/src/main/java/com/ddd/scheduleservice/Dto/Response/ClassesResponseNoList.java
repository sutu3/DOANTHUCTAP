package com.ddd.scheduleservice.Dto.Response;

import com.ddd.scheduleservice.Enum.ClassStatus;
import com.ddd.scheduleservice.Enum.ClassType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    LocalDate startTime;
    LocalDate endTime;
    ClassType type;
    ClassStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

