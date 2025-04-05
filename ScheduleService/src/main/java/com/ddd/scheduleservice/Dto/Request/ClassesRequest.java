package com.ddd.scheduleservice.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

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



}

