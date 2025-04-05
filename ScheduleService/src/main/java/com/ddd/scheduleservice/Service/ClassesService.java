package com.ddd.scheduleservice.Service;

import com.ddd.scheduleservice.Dto.Request.ClassesRequest;
import com.ddd.scheduleservice.Dto.Response.ClassesResponse;
import com.ddd.scheduleservice.Entity.*;
import com.ddd.scheduleservice.Enum.ClassStatus;
import com.ddd.scheduleservice.Enum.ClassType;
import com.ddd.scheduleservice.Exception.AppException;
import com.ddd.scheduleservice.Exception.ErrorCode;
import com.ddd.scheduleservice.Form.ClassesUpdate;
import com.ddd.scheduleservice.Mapper.ClassesMapper;
import com.ddd.scheduleservice.Repo.*;
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

        Subject subjectupdate=subjectRepo.findById(request.getSubject_id())
                .orElseThrow(()->new AppException(ErrorCode.MONHOC_NOT_FOUND));

        Shift shiftupdate=shiftRepo.findById(request.getShift_id())
                .orElseThrow(()->new AppException(ErrorCode.CA_NOT_FOUND));

        Room roomupdate=roomRepo.findById(request.getRoom_id())
                .orElseThrow(()->new AppException(ErrorCode.PHONGMAY_NOT_FOUND));
        entity.setStartTime(shiftupdate.getStartTime());
        entity.setEndTime(shiftupdate.getEndTime());
        entity.setType(ClassType.OFFLINE);
        entity.setStatus(ClassStatus.DANG_DIEN_RA);
        entity.setUser(userupdate);
        entity.setSubject(subjectupdate);
        entity.setShift(shiftupdate);
        entity.setRoom(roomupdate);
        return classesMapper.toClassesResponse(classesRepo.save(entity));
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
        entity.setStartTime(shiftupdate.getStartTime());
        entity.setEndTime(shiftupdate.getEndTime());
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