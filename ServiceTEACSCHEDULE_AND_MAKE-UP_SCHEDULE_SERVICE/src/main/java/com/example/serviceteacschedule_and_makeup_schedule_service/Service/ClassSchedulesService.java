package com.example.serviceteacschedule_and_makeup_schedule_service.Service;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.ClassSchedulesRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ClassSchedulesResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.ClassSchedules;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Classes;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Room;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Shift;
import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.DayOfWeek;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.AppException;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.ErrorCode;
import com.example.serviceteacschedule_and_makeup_schedule_service.Mapper.ClassSchedulesMapper;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.ClassSchedulesRepo;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.ClassesRepo;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.RoomRepo;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.ShiftRepo;
import com.example.serviceteacschedule_and_makeup_schedule_service.Util.DayOfWeekUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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