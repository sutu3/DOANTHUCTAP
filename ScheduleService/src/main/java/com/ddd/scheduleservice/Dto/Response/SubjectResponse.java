package com.ddd.scheduleservice.Dto.Response;

import com.ddd.scheduleservice.Entity.MakeupRequest;
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
    /*List<MakeupRequest> makeupRequests;*/
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
