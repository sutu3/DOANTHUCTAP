package com.ddd.scheduleservice.Dto.Response;

import com.ddd.scheduleservice.Enum.RequestStatus;
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
     UserResponseNoList user;
    RoomResponse room;
    SubjectResponseNoList subject;
     LocalDateTime requestTime;
     RequestStatus status;
    String reason;
    LocalDateTime approvedAt;
    LocalDateTime makeupDate;
}

