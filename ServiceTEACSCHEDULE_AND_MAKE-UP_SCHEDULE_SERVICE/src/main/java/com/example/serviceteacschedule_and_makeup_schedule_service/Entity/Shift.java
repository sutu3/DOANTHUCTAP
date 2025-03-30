package com.example.serviceteacschedule_and_makeup_schedule_service.Entity;

import java.time.LocalTime;
import java.util.List;

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
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int shiftId;
    @Column(columnDefinition = "varchar(255) COMMENT tên ca",nullable = false)
    String shiftName;
    @OneToMany(mappedBy ="shift",cascade = CascadeType.ALL,orphanRemoval = true)
    List<ClassSchedules> classesSchedules;
    @OneToMany(mappedBy ="shift",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Classes> classes;

    @Column(columnDefinition = "DATE COMMENT thời gian bắt đầu",nullable = false)
    LocalTime startTime;
    @Column(columnDefinition = "DATE COMMENT thời gian kết thúc",nullable = false)
    LocalTime endTime;
}
