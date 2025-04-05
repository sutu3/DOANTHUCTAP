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
public class RoomResponse {
    int roomId;

    String roomName;

    int capacity;

    private String location;
/*
    List<ClassSchedulesResponse> classesSchedules;
    List<MakeupRequest> makeupRequests;
    List<ClassesResponse> classes;*/
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
