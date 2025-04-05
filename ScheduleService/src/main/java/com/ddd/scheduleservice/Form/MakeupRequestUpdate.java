package com.ddd.scheduleservice.Form;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MakeupRequestUpdate {
    int shift_id;
    LocalDateTime makeupDate;
}

