package com.ddd.scheduleservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int shiftId;
    @Column(columnDefinition = "varchar(255) COMMENT 'tên ca'",nullable = false)
    String shiftName;
    @OneToMany(mappedBy ="shift",cascade = CascadeType.ALL,orphanRemoval = true)
    List<ClassSchedules> classesSchedules;
    @OneToMany(mappedBy ="shift",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Classes> classes;

    @Column(columnDefinition = "TIME COMMENT 'thời gian bắt đầu'", nullable = false)
    LocalTime startTime; // Giờ bắt đầu ca học

    @Column(columnDefinition = "TIME COMMENT 'thời gian kết thúc'", nullable = false)
    LocalTime endTime;
}
