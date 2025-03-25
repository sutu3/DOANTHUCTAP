package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GiangvienResponse {
    String msCb;
    String tenCb;
}
