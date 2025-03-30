package com.example.serviceteacschedule_and_makeup_schedule_service.Entity;

import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.DayOfWeek;
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

    @Enumerated(EnumType.STRING)
    DayOfWeek dayOfWeek;
}

