package com.ddd.scheduleservice.Service;

import com.ddd.scheduleservice.Dto.Request.ClassSchedulesRequest;
import com.ddd.scheduleservice.Dto.Response.ClassSchedulesResponse;
import com.ddd.scheduleservice.Entity.ClassSchedules;
import com.ddd.scheduleservice.Entity.Classes;
import com.ddd.scheduleservice.Entity.Room;
import com.ddd.scheduleservice.Entity.Shift;
import com.ddd.scheduleservice.Enum.DayOfWeek;
import com.ddd.scheduleservice.Exception.AppException;
import com.ddd.scheduleservice.Exception.ErrorCode;
import com.ddd.scheduleservice.Mapper.ClassSchedulesMapper;
import com.ddd.scheduleservice.Repo.ClassSchedulesRepo;
import com.ddd.scheduleservice.Repo.ClassesRepo;
import com.ddd.scheduleservice.Repo.RoomRepo;
import com.ddd.scheduleservice.Repo.ShiftRepo;
import com.ddd.scheduleservice.Util.DayOfWeekUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ClassSchedulesService {
    private final ClassSchedulesMapper classSchedulesMapper;

    ClassSchedulesRepo classSchedulesRepo;
    ShiftRepo shiftRepo;
    RoomRepo roomRepo;
    ClassesRepo classesRepo;
    DayOfWeekUtil dayOfWeekUtil;

    public List<ClassSchedulesResponse> getAllClassSchedules() {
        return classSchedulesRepo.findAll().stream()
                .map(classSchedulesMapper::toClassSchedulesResponse)
                .collect(Collectors.toList());
    }

    
    public ClassSchedulesResponse getClassScheduleById(int id) {
        return classSchedulesMapper.toClassSchedulesResponse(
                classSchedulesRepo.findById(id).orElseThrow(
                        ()->new AppException(ErrorCode.LICHGIANGDAY_NOT_FOUND)));
    }
    public ClassSchedulesResponse createClassSchedule(ClassSchedulesRequest request) {

        DayOfWeek dayOfWeek=dayOfWeekUtil.dayOfWeek(request.getDayOfWeek());
        if( classSchedulesRepo.findFirstByClasses_Shift_ShiftIdAndDayOfWeek(
                request.getShift_id(),dayOfWeek)!=null){
            throw new AppException(ErrorCode.CLASS_SCHEDULES_IS_EXIST);
        }
        ClassSchedules schedules=classSchedulesMapper.toClassSchedules(request);
        Shift shiftupdate=shiftRepo.findById(request.getShift_id())
                .orElseThrow(()->new AppException(ErrorCode.CA_NOT_FOUND));
        schedules.setShift(shiftupdate);
        Room roomupdate=roomRepo.findById(request.getRoom_id())
                .orElseThrow(()->new AppException(ErrorCode.PHONGMAY_NOT_FOUND));
        schedules.setRoom(roomupdate);
        Classes classes=classesRepo.findById(request.getClass_id())
                .orElseThrow(()->new AppException(ErrorCode.LOP_NOT_FOUND));
        schedules.setClasses(classes);
        schedules.setDayOfWeek(dayOfWeek);
        return classSchedulesMapper
                .toClassSchedulesResponse(classSchedulesRepo.save(schedules));
    }

   
    /*public ClassSchedules updateClassSchedule(int id, ClassSchedules scheduleDetails) {
        ClassSchedules schedules=classSchedulesMapper.
        return classSchedulesRepo.findById(id).map(existingSchedule -> {
            existingSchedule.setClasses(scheduleDetails.getClasses());
            existingSchedule.setRoom(scheduleDetails.getRoom());
            existingSchedule.setShift(scheduleDetails.getShift());
            existingSchedule.setDayOfWeek(scheduleDetails.getDayOfWeek());
            return classSchedulesRepo.save(existingSchedule);
        }).orElseThrow(() -> new RuntimeException("Không tìm thấy lịch học với ID: " + id));
    }*/

  
    public void deleteClassSchedule(int id) {
        classSchedulesRepo.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.LICHGIANGDAY_NOT_FOUND));
        classSchedulesRepo.deleteById(id);
    }
}