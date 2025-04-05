package com.ddd.scheduleservice.Repo;

import com.ddd.scheduleservice.Entity.ClassSchedules;
import com.ddd.scheduleservice.Enum.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassSchedulesRepo extends JpaRepository<ClassSchedules, Integer> {
    ClassSchedules findFirstByClasses_Shift_ShiftIdAndDayOfWeek(int shiftId, DayOfWeek dayOfWeek);
    
}