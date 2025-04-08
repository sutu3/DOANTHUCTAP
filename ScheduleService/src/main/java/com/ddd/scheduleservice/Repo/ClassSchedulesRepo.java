package com.ddd.scheduleservice.Repo;

import com.ddd.scheduleservice.Entity.ClassSchedules;
import com.ddd.scheduleservice.Enum.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ClassSchedulesRepo extends JpaRepository<ClassSchedules, Integer> {
    ClassSchedules findFirstByClasses_Shift_ShiftIdAndDayOfWeekAndDateStart(int shiftId, DayOfWeek dayOfWeek, LocalDate date);
    ClassSchedules findByClasses_ClassId(int class_id);
}