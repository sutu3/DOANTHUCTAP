package com.example.serviceteacschedule_and_makeup_schedule_service.Entity;

import java.time.LocalDateTime;

import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.ReadStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int notificationId;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(columnDefinition = "varchar(255) COMMENT tin nhan gui",nullable = false)
    String message;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    LocalDateTime notificationTime;

    @Enumerated(EnumType.STRING)
    ReadStatus readStatus = ReadStatus.UNREAD;
}

