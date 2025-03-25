package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonhocRequest {
    String tenMh;


}
