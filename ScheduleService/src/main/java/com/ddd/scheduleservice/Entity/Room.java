package com.ddd.scheduleservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int roomId;

    @Column(columnDefinition = "varchar(255) COMMENT 'ten phong'",nullable = false)
    String roomName;

    @Column(columnDefinition = "varchar(255) COMMENT 'sức chứa tối đa'",nullable = false)
    int capacity;

    @Column(columnDefinition = "varchar(255) COMMENT 'vị trí phòng'",nullable = false)
    private String location;

    @OneToMany(mappedBy ="room",cascade = CascadeType.ALL,orphanRemoval = true)
    List<ClassSchedules> classesSchedules;
    @OneToMany(mappedBy ="room",cascade = CascadeType.ALL,orphanRemoval = true)
    List<MakeupRequest> makeupRequests;
    @OneToMany(mappedBy ="room",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Classes> classes;
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @UpdateTimestamp
    LocalDateTime updatedAt;
}
