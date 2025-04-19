package com.ddd.scheduleservice.Dto.Response;


import com.ddd.scheduleservice.Entity.User;
import com.ddd.scheduleservice.Enum.ReadStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationResponse {

    String notificationId;

    UserResponseNoList user;

    String message;

    LocalDateTime notificationTime;

    ReadStatus readStatus = ReadStatus.UNREAD;
}

