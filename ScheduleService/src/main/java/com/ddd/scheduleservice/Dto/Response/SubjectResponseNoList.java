package com.ddd.scheduleservice.Dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectResponseNoList {
    int subjectId;

    String subjectName;

    String description;
/*
    List<ClassesResponse> classes;
    List<MakeupRequest> makeupRequests;*/
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
