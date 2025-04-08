package com.ddd.scheduleservice.Service;

import com.ddd.scheduleservice.Client.NotificationService;
import com.ddd.scheduleservice.Dto.Request.*;
import com.ddd.scheduleservice.Dto.Response.ClassSchedulesResponse;
import com.ddd.scheduleservice.Dto.Response.MakeupRequestDTOResponse;
import com.ddd.scheduleservice.Entity.*;
import com.ddd.scheduleservice.Enum.RequestStatus;
import com.ddd.scheduleservice.Exception.AppException;
import com.ddd.scheduleservice.Exception.ErrorCode;
import com.ddd.scheduleservice.Form.MakeupRequestUpdate;
import com.ddd.scheduleservice.Mapper.ClassSchedulesMapper;
import com.ddd.scheduleservice.Mapper.MakeupRequestMapper;
import com.ddd.scheduleservice.Repo.*;
import com.ddd.scheduleservice.Util.DayOfWeekUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class MakeupRequestService {

    MakeupRequestRepo makeupRequestRepo;
    MakeupRequestMapper makeupRequestMapper;
    NotificationService notificationService;
    UserRepo userRepo;
    AuthenService authenService;
    RoomRepo roomRepo;
    SubjectRepo subjectRepo;
    ClassesRepo classesRepo;
    ClassesService classesService;
    ClassSchedulesService classSchedulesService;
    ClassSchedulesMapper classSchedulesMapper;
    DayOfWeekUtil dayOfWeekUtil;
    private final ShiftRepo shiftRepo;


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

        User user =userRepo.findById(request.getUser_id())
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

    public MakeupRequestDTOResponse RejectRequest(int id,String token) {
        authenService.authenAdmin(TokenRequest.builder()
                .Token(token)
                .build());
        MakeupRequest makeupRequest=makeupRequestRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.DANGKYBU_NOT_FOUND));
        makeupRequest.setStatus(RequestStatus.REJECTED);
        MakeupRequest makeupRequestUpdate=makeupRequestRepo.save(makeupRequest);
        notificationService.sendMailReject(NotificationReject.builder()
                        .id(makeupRequestUpdate.getRequestId())
                        .to(makeupRequestUpdate.getUser().getEmail())
                        .from("minhdaimk111@gmail.com")
                        .subject(makeupRequestUpdate.getSubject().getSubjectName())
                        .toName(makeupRequestUpdate.getUser().getFullname())
                        .message("Yêu cầu bù lớp của bạn không được phê duyệt")
                        .content("Thông báo từ chối đăng ký học bù")
                        .room(makeupRequestUpdate.getRoom().getRoomName())
                .build());
        return makeupRequestMapper.toMakeupRequestDTOResponse(makeupRequestUpdate);
    }
    public ClassSchedulesResponse Approve(int id, MakeupRequestUpdate update,String token){

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
        Shift shift=shiftRepo.findById(update.getShift_id())
                .orElseThrow(()->new AppException(ErrorCode.CA_NOT_FOUND));
        if(classes==null){
            classId=classesService.createClass(ClassesRequest.builder()
                    .subject_id(makeupRequest.getSubject().getSubjectId())
                            .endTime(makeupRequest.getMakeupDate().toLocalDate().plusDays(1))
                            .startTime(makeupRequest.getMakeupDate().toLocalDate())
                            .user_id(makeupRequest.getUser().getUserId())
                            .shift_id(shift.getShiftId())
                            .room_id(makeupRequest.getRoom().getRoomId())
                    .build(),token).getClassId();
        }else {
            classId = classes.getClassId();
        }
        MakeupRequest makeupRequestApprove=makeupRequestRepo.save(makeupRequest);

       /* ClassSchedulesResponse classSchedulesResponse=classSchedulesService.createClassSchedule(
                ClassSchedulesRequest.builder()
                .class_id(classId)
                .room_id(makeupRequestApprove.getRoom().getRoomId())
                .shift_id(update.getShift_id())
                .dayOfWeek(makeupRequestApprove.getMakeupDate())
                .build());*/
        notificationService.sendMailApprove(NotificationApprove.builder()
                .id(makeupRequestApprove.getRequestId())
                .to(makeupRequestApprove.getUser().getEmail())
                .from("minhdaimk111@gmail.com")
                .subject(makeupRequestApprove.getSubject().getSubjectName())
                .toName(makeupRequestApprove.getUser().getFullname())
                .message("Yêu cầu bù lớp của bạn đã được phê duyệt")
                .content("Thông báo về việc đăng ký học bù")
                .date(makeupRequestApprove.getMakeupDate().toString())
                .shift(new StringBuilder().append(shift.getShiftName())
                                .append("(")
                                .append(shift.getStartTime())
                                .append("-")
                                .append(shift.getEndTime())
                                .append(")")
                                .toString())
                .classification(makeupRequestApprove.getClass().getName())
                .build());

        return classSchedulesResponse;
    }


    public void deleteMakeupRequest(int id) {
        makeupRequestRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.DANGKYBU_NOT_FOUND));
        makeupRequestRepo.deleteById(id);
    }
}