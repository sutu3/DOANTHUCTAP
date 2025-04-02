package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response;

import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.ClassSchedules;
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
