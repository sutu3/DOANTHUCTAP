package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassSchedulesRequest {
    int class_id;
    int room_id;
    int shift_id;
    LocalDateTime dayOfWeek;
}

