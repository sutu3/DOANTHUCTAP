package com.example.serviceteacschedule_and_makeup_schedule_service.Service;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.ClassSchedulesRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.ClassesRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.MakeupRequestDTORequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ClassSchedulesResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ClassesResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.MakeupRequestDTOResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.*;
import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.DayOfWeek;
import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.RequestStatus;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.AppException;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.ErrorCode;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.MakeupRequestUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Mapper.ClassSchedulesMapper;
import com.example.serviceteacschedule_and_makeup_schedule_service.Mapper.MakeupRequestMapper;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.*;
import com.example.serviceteacschedule_and_makeup_schedule_service.Util.DayOfWeekUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class MakeupRequestService {

    MakeupRequestRepo makeupRequestRepo;
    MakeupRequestMapper makeupRequestMapper;
    private final UserRepo userRepo;
    private final RoomRepo roomRepo;
    private final SubjectRepo subjectRepo;
    private final ClassesRepo classesRepo;
    private final ClassesService classesService;
    private final ClassSchedulesService classSchedulesService;
    ClassSchedulesMapper classSchedulesMapper;
    DayOfWeekUtil dayOfWeekUtil;


    public List<MakeupRequestDTOResponse> getAllMakeupRequests() {
        return makeupRequestRepo.findAll().stream()
                .map(makeupRequestMapper::toMakeupRequestDTOResponse)
                .collect(Collectors.toList());
    }


    public MakeupRequestDTOResponse getMakeupRequestById(int id) {
        return makeupRequestMapper.toMakeupRequestDTOResponse(
                makeupRequestRepo.findById(id).orElseThrow(()->
                        new AppException(ErrorCode.DANGKYBU_NOT_FOUND)));
    }
    public MakeupRequestDTOResponse createMakeupRequest(MakeupRequestDTORequest request) {
        MakeupRequest makeupRequest=makeupRequestMapper.toMakeupRequestDTO(request);

        User user=userRepo.findById(request.getUser_id())
                .orElseThrow(()->new AppException(ErrorCode.GIANGVIEN_NOT_FOUND));
        Room room=roomRepo.findById(request.getRoom_id())
                .orElseThrow(()->new AppException(ErrorCode.PHONGMAY_NOT_FOUND));
        Subject subject=subjectRepo.findById(request.getSubject_id())
                .orElseThrow(()->new AppException(ErrorCode.MONHOC_NOT_FOUND));
        makeupRequest.setStatus(RequestStatus.PENDING);
        makeupRequest.setUser(user);
        makeupRequest.setRoom(room);
        makeupRequest.setSubject(subject);
        return makeupRequestMapper.toMakeupRequestDTOResponse(
                makeupRequestRepo.save(makeupRequest));
    }

    public MakeupRequestDTOResponse RejectRequest(int id) {
        MakeupRequest makeupRequest=makeupRequestRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.DANGKYBU_NOT_FOUND));
        makeupRequest.setStatus(RequestStatus.REJECTED);
        return makeupRequestMapper.toMakeupRequestDTOResponse(
                makeupRequestRepo.save(makeupRequest));
    }
    public ClassSchedulesResponse Approve(int id, MakeupRequestUpdate update){
        MakeupRequest makeupRequest=makeupRequestRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.DANGKYBU_NOT_FOUND));
        makeupRequest.setStatus(RequestStatus.APPROVED);
        makeupRequest.setApprovedAt(LocalDateTime.now());
        makeupRequest.setMakeupDate(update.getMakeupDate());
        Classes classes=classesRepo.findFirstBySubject_SubjectIdAndRoom_RoomIdAndUser_UserId(
                makeupRequest.getSubject().getSubjectId(),
                makeupRequest.getRoom().getRoomId(),
                makeupRequest.getUser().getUserId());
        int classId=0;
        if(classes==null){
            classId=classesService.createClass(ClassesRequest.builder()
                    .subject_id(makeupRequest.getSubject().getSubjectId())
                            .user_id(makeupRequest.getUser().getUserId())
                            .shift_id(update.getShift_id())
                            .room_id(makeupRequest.getRoom().getRoomId())
                    .build()).getClassId();
        }else {
            classId = classes.getClassId();
        }
        MakeupRequest makeupRequestApprove=makeupRequestRepo.save(makeupRequest);

        ClassSchedulesResponse classSchedulesResponse=classSchedulesService.createClassSchedule(
                ClassSchedulesRequest.builder()
                .class_id(classId)
                .room_id(makeupRequestApprove.getRoom().getRoomId())
                .shift_id(update.getShift_id())
                .dayOfWeek(makeupRequestApprove.getMakeupDate())
                .build());


        return classSchedulesResponse;
    }


    public void deleteMakeupRequest(int id) {
        makeupRequestRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.DANGKYBU_NOT_FOUND));
        makeupRequestRepo.deleteById(id);
    }
}