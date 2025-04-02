package com.example.serviceteacschedule_and_makeup_schedule_service.Service;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.ClassesRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ClassesResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.*;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.AppException;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.ErrorCode;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.ClassesUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Mapper.ClassesMapper;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.*;
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
public class ClassesService {
    ClassesMapper classesMapper;

    ClassesRepo classesRepo;
    UserRepo userRepo;
    SubjectRepo subjectRepo;
    ShiftRepo shiftRepo;
    RoomRepo roomRepo;


    public List<ClassesResponse> getAll() {
        return classesRepo.findAll().stream()
                .map(classesMapper::toClassesResponse)
                .collect(Collectors.toList());
    }

   
    public ClassesResponse getById(int id) {
        return classesMapper.toClassesResponse( classesRepo.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.LOP_NOT_FOUND)));
    }

    public ClassesResponse createClass(ClassesRequest request) {
        Classes entity=classesMapper.toClasses(request);
        User userupdate=userRepo.findById(request.getUser_id())
                .orElseThrow(()->new AppException(ErrorCode.GIANGVIEN_NOT_FOUND));
        entity.setUser(userupdate);
        Subject subjectupdate=subjectRepo.findById(request.getSubject_id())
                .orElseThrow(()->new AppException(ErrorCode.MONHOC_NOT_FOUND));
        entity.setSubject(subjectupdate);
        Shift shiftupdate=shiftRepo.findById(request.getShift_id())
                .orElseThrow(()->new AppException(ErrorCode.CA_NOT_FOUND));
        entity.setShift(shiftupdate);
        Room roomupdate=roomRepo.findById(request.getRoom_id())
                .orElseThrow(()->new AppException(ErrorCode.PHONGMAY_NOT_FOUND));
        entity.setRoom(roomupdate);
        return classesMapper.toClassesResponse(classesRepo.save(entity));
    }

    public ClassesResponse updateClass(int id, ClassesUpdate request) {
        Classes entity=classesRepo.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.LOP_NOT_FOUND));
        classesMapper.updateClasses(entity,request);
        User userupdate=userRepo.findById(request.getUser_id())
                .orElseThrow(()->new AppException(ErrorCode.GIANGVIEN_NOT_FOUND));
        entity.setUser(userupdate);
        Subject subjectupdate=subjectRepo.findById(request.getSubject_id())
                .orElseThrow(()->new AppException(ErrorCode.MONHOC_NOT_FOUND));
        entity.setSubject(subjectupdate);
        Shift shiftupdate=shiftRepo.findById(request.getShift_id())
                .orElseThrow(()->new AppException(ErrorCode.CA_NOT_FOUND));
        entity.setShift(shiftupdate);
        Room roomupdate=roomRepo.findById(request.getRoom_id())
                .orElseThrow(()->new AppException(ErrorCode.PHONGMAY_NOT_FOUND));
        entity.setRoom(roomupdate);
        return classesMapper.toClassesResponse(classesRepo.save(entity));
    }

  
    public void deleteClass(int id) {
        classesRepo.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.LOP_NOT_FOUND));
        classesRepo.deleteById(id);
    }
}