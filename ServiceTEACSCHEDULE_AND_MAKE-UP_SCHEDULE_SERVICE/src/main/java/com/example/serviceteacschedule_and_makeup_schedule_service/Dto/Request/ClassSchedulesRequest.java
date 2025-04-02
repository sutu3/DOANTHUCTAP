package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request;

import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Classes;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Room;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Shift;
import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.DayOfWeek;
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
public class ClassSchedulesRequest {
    int classes_id;
    int room_id;
    int shift_id;
    LocalDateTime dayOfWeek;
}

