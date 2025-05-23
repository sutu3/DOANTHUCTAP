package com.ddd.scheduleservice.Service;

import com.ddd.scheduleservice.Client.NotificationService;
import com.ddd.scheduleservice.Dto.Request.ClassesRequest;
import com.ddd.scheduleservice.Dto.Request.NotificationRequest;
import com.ddd.scheduleservice.Dto.Request.TokenRequest;
import com.ddd.scheduleservice.Dto.Response.ClassesResponse;
import com.ddd.scheduleservice.Dto.Response.NotificationResponse;
import com.ddd.scheduleservice.Entity.*;
import com.ddd.scheduleservice.Enum.ClassStatus;
import com.ddd.scheduleservice.Enum.ClassType;
import com.ddd.scheduleservice.Enum.DayOfWeek;
import com.ddd.scheduleservice.Exception.AppException;
import com.ddd.scheduleservice.Exception.ErrorCode;
import com.ddd.scheduleservice.Form.ClassesUpdate;
import com.ddd.scheduleservice.Mapper.ClassesMapper;
import com.ddd.scheduleservice.Repo.*;
import com.ddd.scheduleservice.Util.DayOfWeekUtil;
import com.ddd.scheduleservice.Util.IsvalidDate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ClassesService {
    ClassesMapper classesMapper;
    ClassesRepo classesRepo;
    UserRepo userRepo;
    SubjectRepo subjectRepo;
    ShiftRepo shiftRepo;
    RoomRepo roomRepo;
    IsvalidDate isvalidDate;
    DayOfWeekUtil dayOfWeekUtil;
    private final AuthenService authenService;
    private final NotificationService notificationService;
    NotificationScheduleService notificationScheduleService;


    public List<ClassesResponse> getAll() {
        return classesRepo.findAll().stream()
                .map(classesMapper::toClassesResponse)
                .collect(Collectors.toList());
    }

   
    public ClassesResponse getById(int id) {

        return classesMapper.toClassesResponse( classesRepo.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.LOP_NOT_FOUND)));
    }

    public ClassesResponse createClass(ClassesRequest request,String token) {
        authenService.authenAdmin(TokenRequest.builder()
                .token(token)
                .build());
        Classes entity=classesMapper.toClasses(request);

        User userupdate=userRepo.findById(request.getUser_id())
                .orElseThrow(()->new AppException(ErrorCode.GIANGVIEN_NOT_FOUND));

        Subject subjectupdate=subjectRepo.findById(request.getSubject_id())
                .orElseThrow(()->new AppException(ErrorCode.MONHOC_NOT_FOUND));

        Shift shiftupdate=shiftRepo.findById(request.getShift_id())
                .orElseThrow(()->new AppException(ErrorCode.CA_NOT_FOUND));

        Room roomupdate=roomRepo.findById(request.getRoom_id())
                .orElseThrow(()->new AppException(ErrorCode.PHONGMAY_NOT_FOUND));
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setType(ClassType.OFFLINE);
        entity.setStatus(ClassStatus.DANG_DIEN_RA);
        entity.setUser(userupdate);
        entity.setSubject(subjectupdate);
        entity.setShift(shiftupdate);
        entity.setRoom(roomupdate);
        Classes classes=classesRepo.save(entity);
        System.out.print(classes.getClassId());
        DayOfWeek dayOfWeek=dayOfWeekUtil.dayOfWeek(request.getStartTime().atTime(LocalTime.now()));
        isvalidDate.isValidClassSchedule(request.getStartTime(),request.getEndTime(),dayOfWeek,classes);
        NotificationResponse response=notificationScheduleService.createNotification(NotificationRequest.builder()
                .email_user(userupdate.getEmail())
                .message("Quản lý đã tạo ra lịch giảng dạy xin bạn hãy vào trang web xem và sắp xếp lịch").build());
        return classesMapper.toClassesResponse(classes);
    }

    public ClassesResponse updateClass(int id, ClassesUpdate request) {
        Classes entity=classesRepo.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.LOP_NOT_FOUND));

        User userupdate=userRepo.findById(request.getUser_id())
                .orElseThrow(()->new AppException(ErrorCode.GIANGVIEN_NOT_FOUND));

        Subject subjectupdate=subjectRepo.findById(request.getSubject_id())
                .orElseThrow(()->new AppException(ErrorCode.MONHOC_NOT_FOUND));

        Shift shiftupdate=shiftRepo.findById(request.getShift_id())
                .orElseThrow(()->new AppException(ErrorCode.CA_NOT_FOUND));

        Room roomupdate=roomRepo.findById(request.getRoom_id())
                .orElseThrow(()->new AppException(ErrorCode.PHONGMAY_NOT_FOUND));
        classesMapper.updateClasses(entity,request);
        entity.setUser(userupdate);
        entity.setSubject(subjectupdate);
        entity.setRoom(roomupdate);
        entity.setShift(shiftupdate);
        return classesMapper.toClassesResponse(classesRepo.save(entity));
    }
    public void deleteClass(int id) {
        classesRepo.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.LOP_NOT_FOUND));
        classesRepo.deleteById(id);
    }
}