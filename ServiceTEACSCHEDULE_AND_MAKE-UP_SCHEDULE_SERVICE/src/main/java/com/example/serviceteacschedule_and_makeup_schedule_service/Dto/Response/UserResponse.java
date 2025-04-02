package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response;

import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Classes;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.MakeupRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Notification;
import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.Role;
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
public class UserResponse {
    int userId;
    String username;
    String password;
    String fullname;
    String email;
    List<MakeupRequest> makeupRequests;
    List<Classes> classes;
    List<Notification> notifications;
    Role role;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

