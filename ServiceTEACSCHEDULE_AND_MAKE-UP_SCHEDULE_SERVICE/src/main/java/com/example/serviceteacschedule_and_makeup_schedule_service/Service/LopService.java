package com.example.serviceteacschedule_and_makeup_schedule_service.Service;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.LopRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.LopResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.LopResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Lop;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.AppException;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.ErrorCode;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.LopUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.LopUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Mapper.LopMapper;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.LopRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class LopService {
    LopRepo lopRepo;
    LopMapper lopMapper;
    public LopResponse getbyId(String id){
        return lopMapper.toLopResponse(
                lopRepo.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.LOP_NOT_FOUND)));
    }
    public List<LopResponse> getall(){
        return lopRepo.findAll().stream().map(lopMapper::toLopResponse)
                .collect(Collectors.toList());
    }
    public LopResponse create(LopRequest request){
        Lop lop= lopMapper.toLop(request);
        return lopMapper.toLopResponse(
                lopRepo.save(lop));
    }
    public LopResponse update(String id, LopUpdate request){
        Lop Lop= lopRepo.findById(id)
               .orElseThrow(() -> new AppException(ErrorCode.LOP_NOT_FOUND));
        lopMapper.updateLop(Lop,request);
        return lopMapper.toLopResponse(
                lopRepo.save(Lop));
    }
    public void delete(String id){
        lopRepo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.LOP_NOT_FOUND));
        lopRepo.deleteById(id);
    }

}
