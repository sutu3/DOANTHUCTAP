package com.example.serviceteacschedule_and_makeup_schedule_service.Service;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.ShiftRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ShiftResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Shift;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.AppException;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.ErrorCode;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.ShiftUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Mapper.ShiftMapper;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.ShiftRepo;
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
public class ShiftService {
    private final ShiftMapper shiftMapper;

    ShiftRepo shiftRepo;


    public List<ShiftResponse> getAllShifts() {
        return shiftRepo.findAll()
                .stream().map(shiftMapper::toShiftResponse)
                .collect(Collectors.toList());
    }

   
    public ShiftResponse getShiftById(int id) {
        return shiftMapper.toShiftResponse(
                shiftRepo.findById(id).orElseThrow(()->
                        new AppException(ErrorCode.CA_NOT_FOUND)));
    }


    public ShiftResponse createShift(ShiftRequest request) {
        Shift shift=shiftMapper.toShift(request);
        return shiftMapper.toShiftResponse(shiftRepo.save(shift));
    }


    public ShiftResponse updateShift(int id, ShiftUpdate shiftDetails) {
        Shift shift=shiftRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.CA_NOT_FOUND));
        shiftMapper.updateShift(shift,shiftDetails);
        return shiftMapper.toShiftResponse(shiftRepo.save(shift));
    }

    public void deleteShift(int id) {
        shiftRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.CA_NOT_FOUND));
        shiftRepo.deleteById(id);
    }
}