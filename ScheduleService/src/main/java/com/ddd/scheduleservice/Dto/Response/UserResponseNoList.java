package com.ddd.scheduleservice.Dto.Response;

import com.ddd.scheduleservice.Enum.Role;
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
public class UserResponseNoList {
    int userId;
    String username;
    String password;
    String fullname;
    String email;
    /*List<MakeupRequest> makeupRequests;
    List<Classes> classes;
    List<Notification> notifications;*/
    Role role;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

