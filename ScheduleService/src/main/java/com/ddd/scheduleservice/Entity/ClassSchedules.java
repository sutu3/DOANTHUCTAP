package com.ddd.scheduleservice.Entity;


import com.ddd.scheduleservice.Enum.DayOfWeek;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
public class ClassSchedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer scheduleId;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "classId", nullable = false)
    Classes classes;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "roomId", nullable = false)
    Room room;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "shiftId", nullable = false)
    Shift shift;
    @Column(columnDefinition = "DATE COMMENT 'thoi gian lop hoc mo'",nullable = false)
    LocalDate dateStart;
    @Enumerated(EnumType.STRING)
    DayOfWeek dayOfWeek;
}

