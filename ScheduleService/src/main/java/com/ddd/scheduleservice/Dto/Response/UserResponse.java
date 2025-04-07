package com.ddd.scheduleservice.Dto.Response;

import com.ddd.scheduleservice.Entity.Classes;
import com.ddd.scheduleservice.Entity.MakeupRequest;
import com.ddd.scheduleservice.Entity.Notification;
import com.ddd.scheduleservice.Enum.Role;
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
    String imageUrl;
    List<MakeupRequestDTOResponseNoUser> makeupRequests;
    List<ClassesResponseNoListNoUser> classes;
    Role role;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

