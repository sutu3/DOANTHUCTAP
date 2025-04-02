package com.example.serviceteacschedule_and_makeup_schedule_service.Util;

import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.DayOfWeek;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DayOfWeekUtil {
    public DayOfWeek dayOfWeek(LocalDateTime time){
        int dayOfWeek = time.getDayOfWeek().getValue(); // 1 = Monday, 7 = Sunday
        // Trả về tên của thứ trong tuần tương ứng
        return DayOfWeek.values()[dayOfWeek - 1];
    }
}
