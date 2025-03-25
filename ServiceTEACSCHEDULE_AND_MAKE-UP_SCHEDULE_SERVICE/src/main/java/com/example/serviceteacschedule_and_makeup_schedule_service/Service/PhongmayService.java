package com.example.serviceteacschedule_and_makeup_schedule_service.Service;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.PhongmayRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.PhongmayResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Phongmay;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.AppException;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.ErrorCode;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.PhongmayUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Mapper.PhongmayMapper;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.PhongmayRepo;
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
public class PhongmayService {
    PhongmayRepo PhongmayRepo;
    PhongmayMapper PhongmayMapper;
    public PhongmayResponse getbyId(String id){
        return PhongmayMapper.toPhongmayResponse(
                PhongmayRepo.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.PHONGMAY_NOT_FOUND)));
    }
    public List<PhongmayResponse> getall(){
        return PhongmayRepo.findAll().stream().map(PhongmayMapper::toPhongmayResponse)
                .collect(Collectors.toList());
    }
    public PhongmayResponse create(PhongmayRequest request){
        Phongmay Phongmay= PhongmayMapper.toPhongmay(request);
        return PhongmayMapper.toPhongmayResponse(
                PhongmayRepo.save(Phongmay));
    }
    public PhongmayResponse update(String id, PhongmayUpdate request){
        Phongmay Phongmay= PhongmayRepo.findById(id)
               .orElseThrow(() -> new AppException(ErrorCode.PHONGMAY_NOT_FOUND));
        PhongmayMapper.updatePhongmay(Phongmay,request);
        return PhongmayMapper.toPhongmayResponse(
                PhongmayRepo.save(Phongmay));
    }
    public void delete(String id){
        PhongmayRepo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PHONGMAY_NOT_FOUND));
        PhongmayRepo.deleteById(id);
    }

}
