package com.ddd.scheduleservice.Entity;


import com.ddd.scheduleservice.Enum.ReadStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    String notificationId;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(columnDefinition = "varchar(255) COMMENT 'tin nhan gui'",nullable = false)
    String message;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    LocalDateTime notificationTime;

    @Enumerated(EnumType.STRING)
    ReadStatus readStatus;
}

