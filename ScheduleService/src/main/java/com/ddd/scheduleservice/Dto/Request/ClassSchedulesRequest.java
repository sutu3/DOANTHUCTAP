package com.ddd.scheduleservice.Dto.Request;

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

