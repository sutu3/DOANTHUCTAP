package com.ddd.scheduleservice.Form;

import lombok.*;
import lombok.experimental.FieldDefaults;

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



}

