package com.example.serviceteacschedule_and_makeup_schedule_service.Form;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShiftUpdate {
    String shiftName;
    LocalTime startTime;
    LocalTime endTime;
}
