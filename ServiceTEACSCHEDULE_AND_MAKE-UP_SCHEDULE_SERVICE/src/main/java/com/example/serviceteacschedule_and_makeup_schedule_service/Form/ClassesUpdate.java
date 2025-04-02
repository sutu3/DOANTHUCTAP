package com.example.serviceteacschedule_and_makeup_schedule_service.Form;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassesUpdate {
    int subject_id;

    int room_id;

    int user_id;

    int shift_id;

    LocalDateTime startTime;

    LocalDateTime endTime;


}

