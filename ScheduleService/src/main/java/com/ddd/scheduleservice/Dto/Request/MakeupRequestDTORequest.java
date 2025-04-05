package com.ddd.scheduleservice.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

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

