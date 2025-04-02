package com.example.serviceteacschedule_and_makeup_schedule_service.Service;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.MakeupRequestDTORequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.MakeupRequestDTOResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.MakeupRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Room;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Subject;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.User;
import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.RequestStatus;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.AppException;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.ErrorCode;
import com.example.serviceteacschedule_and_makeup_schedule_service.Mapper.MakeupRequestMapper;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.MakeupRequestRepo;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.RoomRepo;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.SubjectRepo;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.UserRepo;
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
        makeupRequest.setStatus(RequestStatus.PENDING);
        User user=userRepo.findById(request.getUser_id())
                .orElseThrow(()->new AppException(ErrorCode.GIANGVIEN_NOT_FOUND));
        makeupRequest.setUser(user);
        Room room=roomRepo.findById(request.getRoom_id())
                .orElseThrow(()->new AppException(ErrorCode.PHONGMAY_NOT_FOUND));
        makeupRequest.setRoom(room);
        Subject subject=subjectRepo.findById(request.getSubject_id())
                .orElseThrow(()->new AppException(ErrorCode.MONHOC_NOT_FOUND));
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
    public MakeupRequestDTOResponse Approve(int id){
        MakeupRequest makeupRequest=makeupRequestRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.DANGKYBU_NOT_FOUND));
        makeupRequest.setStatus(RequestStatus.APPROVED);
        makeupRequest.setApprovedAt(LocalDateTime.now());
        return makeupRequestMapper.toMakeupRequestDTOResponse(
                makeupRequestRepo.save(makeupRequest));
    }


    public void deleteMakeupRequest(int id) {
        makeupRequestRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.DANGKYBU_NOT_FOUND));
        makeupRequestRepo.deleteById(id);
    }
}