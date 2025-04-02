package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request;

import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Classes;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.MakeupRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Notification;
import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    String username;
    String password;
    String fullname;
    String email;
}

