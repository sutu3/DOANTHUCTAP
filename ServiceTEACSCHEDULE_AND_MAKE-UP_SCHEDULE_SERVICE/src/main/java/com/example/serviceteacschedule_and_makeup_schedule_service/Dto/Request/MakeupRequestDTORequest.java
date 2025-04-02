package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request;

import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Room;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Subject;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.User;
import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.RequestStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MakeupRequestDTORequest {
    int user_id;
    int room_id;
    int subject_id;
    LocalDateTime requestTime;
    String reason;
}

