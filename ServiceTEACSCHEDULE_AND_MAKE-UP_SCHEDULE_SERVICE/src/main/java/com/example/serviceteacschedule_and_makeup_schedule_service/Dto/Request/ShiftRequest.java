package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request;

import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.ClassSchedules;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Classes;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShiftRequest {
    String shiftName;
    LocalTime startTime;
    LocalTime endTime;
}
