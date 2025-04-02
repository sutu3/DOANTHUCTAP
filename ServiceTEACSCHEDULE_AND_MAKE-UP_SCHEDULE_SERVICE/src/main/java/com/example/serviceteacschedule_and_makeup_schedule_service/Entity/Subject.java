package com.example.serviceteacschedule_and_makeup_schedule_service.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int subjectId;

    @Column(columnDefinition = "varchar(255) COMMENT 'tên môn học'",nullable = false)
    String subjectName;

    @Column(columnDefinition = "varchar(255) COMMENT 'mô tả'",nullable = false)
    String description;

    @OneToMany(mappedBy ="subject",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Classes> classes;
    @OneToMany(mappedBy ="subject",cascade = CascadeType.ALL,orphanRemoval = true)
    List<MakeupRequest> makeupRequests;
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @UpdateTimestamp
    LocalDateTime updatedAt;
}
