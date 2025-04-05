package com.ddd.scheduleservice.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

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
