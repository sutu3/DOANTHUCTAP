package com.ddd.scheduleservice.Util;

import com.ddd.scheduleservice.Dto.Request.ClassSchedulesRequest;
import com.ddd.scheduleservice.Entity.Classes;
import com.ddd.scheduleservice.Enum.DayOfWeek;
import com.ddd.scheduleservice.Exception.AppException;
import com.ddd.scheduleservice.Exception.ErrorCode;
import com.ddd.scheduleservice.Repo.ClassesRepo;
import com.ddd.scheduleservice.Service.ClassSchedulesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class IsvalidDate {

    ClassSchedulesService classSchedulesService;
    ClassesRepo classesRepo;
    public DayOfWeek dayOfWeek(LocalDateTime time){
        int dayOfWeek = time.getDayOfWeek().getValue(); // 1 = Monday, 7 = Sunday
        // Trả về tên của thứ trong tuần tương ứng
        return DayOfWeek.values()[dayOfWeek - 1];
    }
    public void isValidClassSchedule(LocalDate startDate, LocalDate endDate, DayOfWeek dayOfWeek,Classes classes) {

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
             DayOfWeek dayOfWeek1=dayOfWeek(currentDate.atStartOfDay());
            if (dayOfWeek1 == dayOfWeek) {
                classSchedulesService.createClassSchedule(ClassSchedulesRequest.builder()
                        .class_id(classes.getClassId())
                                .dayOfWeek(currentDate.atStartOfDay())

                        .room_id(classes.getRoom().getRoomId())
                        .shift_id(classes.getShift().getShiftId())
                        .build());
            }
            currentDate = currentDate.plusDays(1);
        }

    }
}
