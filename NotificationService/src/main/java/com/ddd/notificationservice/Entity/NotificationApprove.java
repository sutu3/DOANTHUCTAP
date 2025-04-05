package com.ddd.notificationservice.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationApprove {
    String from;
    String to;
    String toName;
    String content;
    String message;
    int id;
    String classification;
    String subject;
    String date;
    String shift;
}

