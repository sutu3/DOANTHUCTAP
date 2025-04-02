package com.example.serviceteacschedule_and_makeup_schedule_service.Entity;

import java.time.LocalDateTime;

import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.RequestStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
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
public class MakeupRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer requestId;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "userId", nullable = false)
     User user;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "roomId", nullable = false)
     Room room;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "subjectId", nullable = false)
     Subject subject;

    @Column(columnDefinition = "Date COMMENT 'thoi gian lap dang ky du'",nullable = false)
     LocalDateTime requestTime;

    @Enumerated(EnumType.STRING)
     RequestStatus status;
    @Column(columnDefinition = "varchar(255) COMMENT 'ly do lap dang ky du'",nullable = false)
    String reason;
    @Column(columnDefinition = "Date COMMENT 'thoi gian chap nhan dang ky du'",nullable = false)
    LocalDateTime approvedAt;

    @Column(columnDefinition = "Date COMMENT 'thoi gian hoc du'",nullable = false)
    LocalDateTime makeupDate;
}

