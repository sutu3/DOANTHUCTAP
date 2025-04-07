package com.ddd.scheduleservice.Service;

import com.ddd.scheduleservice.Dto.Request.ShiftRequest;
import com.ddd.scheduleservice.Dto.Request.TokenRequest;
import com.ddd.scheduleservice.Dto.Response.ShiftResponse;
import com.ddd.scheduleservice.Entity.Shift;
import com.ddd.scheduleservice.Exception.AppException;
import com.ddd.scheduleservice.Exception.ErrorCode;
import com.ddd.scheduleservice.Form.ShiftUpdate;
import com.ddd.scheduleservice.Mapper.ShiftMapper;
import com.ddd.scheduleservice.Repo.ShiftRepo;
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
public class ShiftService {
    private final ShiftMapper shiftMapper;

    ShiftRepo shiftRepo;
    private final AuthenService authenService;


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


    public ShiftResponse createShift(ShiftRequest request,String token) {
        authenService.authenAdmin(TokenRequest.builder()
                .Token(token)
                .build());
        Shift shift=shiftMapper.toShift(request);
        return shiftMapper.toShiftResponse(shiftRepo.save(shift));
    }


    public ShiftResponse updateShift(int id, ShiftUpdate shiftDetails,String token) {
        authenService.authenAdmin(TokenRequest.builder()
                .Token(token)
                .build());
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