package com.ddd.scheduleservice.Entity;


import com.ddd.scheduleservice.Enum.RequestStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
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

    @Column(columnDefinition = "DATETIME COMMENT 'Thời gian lập đăng ký dự'", nullable = false)
    private LocalDateTime requestTime;

    @Enumerated(EnumType.STRING)
    RequestStatus status;
    @Column(columnDefinition = "varchar(255) COMMENT 'ly do lap dang ky du'",nullable = false)
    String reason;
    @Column(columnDefinition = "Date COMMENT 'thoi gian chap nhan dang ky du'")
    LocalDateTime approvedAt;

    @Column(columnDefinition = "Date COMMENT 'thoi gian hoc du'")
    LocalDateTime makeupDate;
}

