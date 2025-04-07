package com.ddd.scheduleservice.Entity;

import com.ddd.scheduleservice.Enum.ClassStatus;
import com.ddd.scheduleservice.Enum.ClassType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int classId;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "subjectId", nullable = false)
     Subject subject;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "roomId", nullable = false)
     Room room;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "userId", nullable = false)
    User user;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "shiftId", nullable = false)
     Shift shift;
    @OneToMany(mappedBy ="classes",cascade = CascadeType.ALL,orphanRemoval = true)
    List<ClassSchedules> classesSchedules;

    @Column(columnDefinition = "DATE COMMENT 'thoi gian lop hoc bat dau'",nullable = false)
    LocalDate startTime;

    @Column(columnDefinition = "DATE COMMENT 'thoi gian lop hoc ket thuc'",nullable = false)
    LocalDate endTime;

    @Enumerated(EnumType.STRING)
    ClassType type;

    @Enumerated(EnumType.STRING)
    ClassStatus status;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @UpdateTimestamp
    LocalDateTime updatedAt;
}

