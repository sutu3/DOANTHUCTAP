package com.example.serviceteacschedule_and_makeup_schedule_service.Service;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.MonhocRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.MonhocResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.MonhocResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Monhoc;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.AppException;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.ErrorCode;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.MonhocUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Mapper.MonhocMapper;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.MonhocRepo;
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
public class MonhocService {
    MonhocRepo monhocRepo;
    MonhocMapper monhocMapper;
    public MonhocResponse getbyId(String id){
        return monhocMapper.toMonhocResponse(
                monhocRepo.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.MONHOC_NOT_FOUND)));
    }
    public List<MonhocResponse> getall(){
        return monhocRepo.findAll().stream().map(monhocMapper::toMonhocResponse)
                .collect(Collectors.toList());
    }
    public MonhocResponse create(MonhocRequest request){
        Monhoc Monhoc= monhocMapper.toMonhoc(request);
        return monhocMapper.toMonhocResponse(
                monhocRepo.save(Monhoc));
    }
    public MonhocResponse update(String id, MonhocUpdate request){
        Monhoc Monhoc= monhocRepo.findById(id)
               .orElseThrow(() -> new AppException(ErrorCode.MONHOC_NOT_FOUND));
        monhocMapper.updateMonhoc(Monhoc,request);
        return monhocMapper.toMonhocResponse(
                monhocRepo.save(Monhoc));
    }
    public void delete(String id){
        monhocRepo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MONHOC_NOT_FOUND));
        monhocRepo.deleteById(id);
    }

}
