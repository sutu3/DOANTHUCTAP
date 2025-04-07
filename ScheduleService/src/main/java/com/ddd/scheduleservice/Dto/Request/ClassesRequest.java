package com.ddd.scheduleservice.Dto.Request;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassesRequest {
    int subject_id;

    int room_id;

    int user_id;

    int shift_id;
    LocalDate startTime;
    LocalDate endTime;



}

