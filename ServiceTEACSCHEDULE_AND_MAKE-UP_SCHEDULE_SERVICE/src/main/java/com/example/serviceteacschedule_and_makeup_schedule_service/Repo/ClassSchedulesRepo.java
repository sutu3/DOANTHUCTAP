package com.example.serviceteacschedule_and_makeup_schedule_service.Repo;

import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.ClassSchedules;
import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassSchedulesRepo extends JpaRepository<ClassSchedules, Integer> {
    ClassSchedules findFirstByClasses_Shift_ShiftIdAndDayOfWeek(int shiftId, DayOfWeek dayOfWeek);
    
}