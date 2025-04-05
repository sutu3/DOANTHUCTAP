package com.ddd.scheduleservice.Util;

import com.ddd.scheduleservice.Enum.DayOfWeek;
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
