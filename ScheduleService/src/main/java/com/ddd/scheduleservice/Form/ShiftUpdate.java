package com.ddd.scheduleservice.Form;

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
