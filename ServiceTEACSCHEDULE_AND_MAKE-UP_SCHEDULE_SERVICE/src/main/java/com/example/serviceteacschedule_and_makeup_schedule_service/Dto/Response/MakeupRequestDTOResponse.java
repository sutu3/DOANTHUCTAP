package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response;

import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Room;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Subject;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.User;
import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.RequestStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MakeupRequestDTOResponse {
     Integer requestId;
    User user;
    Room room;
    Subject subject;
     LocalDateTime requestTime;
     RequestStatus status;
    String reason;
    LocalDateTime approvedAt;
    LocalDateTime makeupDate;
}

