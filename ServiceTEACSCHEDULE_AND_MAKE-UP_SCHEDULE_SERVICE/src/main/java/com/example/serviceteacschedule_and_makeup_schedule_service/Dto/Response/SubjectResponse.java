package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response;

import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Classes;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.MakeupRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectResponse {
    int subjectId;

    String subjectName;

    String description;

    List<ClassesResponse> classes;
    List<MakeupRequest> makeupRequests;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
