package com.ddd.notificationservice.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationReject {
    String from;
    String to;
    String toName;
    String content;
    String message;
    int id;
    String room;
    String subject;
}

