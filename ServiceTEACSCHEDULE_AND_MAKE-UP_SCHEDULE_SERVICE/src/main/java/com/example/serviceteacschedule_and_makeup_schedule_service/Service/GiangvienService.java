package com.example.serviceteacschedule_and_makeup_schedule_service.Service;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.GiangvienRequet;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.GiangvienResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Giangvien;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.AppException;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.ErrorCode;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.GiangvienUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Mapper.GiangvienMapper;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.GiangvienRepo;
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
public class GiangvienService {
    GiangvienRepo giangvienRepo;
    GiangvienMapper giangvienMapper;
    public GiangvienResponse getbyId(String id){
        return giangvienMapper.toGiangvienResponse(
                giangvienRepo.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.GIANGVIEN_NOT_FOUND)));
    }
    public List<GiangvienResponse> getall(){
        return giangvienRepo.findAll().stream().map(giangvienMapper::toGiangvienResponse)
                .collect(Collectors.toList());
    }
    public GiangvienResponse create(GiangvienRequet request){
        Giangvien giangvien=giangvienMapper.toGiangvien(request);
        return giangvienMapper.toGiangvienResponse(
                giangvienRepo.save(giangvien));
    }
    public GiangvienResponse update(String id, GiangvienUpdate request){
        Giangvien giangvien=giangvienRepo.findById(id)
               .orElseThrow(() -> new AppException(ErrorCode.GIANGVIEN_NOT_FOUND));
        giangvienMapper.updateGiangvien(giangvien,request);
        return giangvienMapper.toGiangvienResponse(
                giangvienRepo.save(giangvien));
    }
    public void delete(String id){
        giangvienRepo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.GIANGVIEN_NOT_FOUND));
        giangvienRepo.deleteById(id);
    }

}
