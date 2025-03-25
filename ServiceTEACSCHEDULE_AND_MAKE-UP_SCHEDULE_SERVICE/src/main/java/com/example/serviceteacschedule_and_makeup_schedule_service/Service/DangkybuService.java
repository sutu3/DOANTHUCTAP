package com.example.serviceteacschedule_and_makeup_schedule_service.Service;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.DangkybuRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.DangkybuResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.*;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.AppException;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.ErrorCode;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.DangkybuUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Mapper.DangkybuMapper;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.*;
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
public class DangkybuService {
    DangkybuRepo DangkybuRepo;
    DangkybuMapper DangkybuMapper;
    private final GiangvienRepo giangvienRepo;
    private final LopRepo lopRepo;
    private final PhongmayRepo phongmayRepo;
    private final MonhocRepo monhocRepo;

    public DangkybuResponse getbyId(String id){
        return DangkybuMapper.toDangkybuResponse(
                DangkybuRepo.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.DANGKYBU_NOT_FOUND)));
    }
    public List<DangkybuResponse> getall(){
        return DangkybuRepo.findAll().stream().map(DangkybuMapper::toDangkybuResponse)
                .collect(Collectors.toList());
    }
    public DangkybuResponse create(DangkybuRequest request){
        Monhoc monhoc=monhocRepo.findById(request.getMaMh())
                .orElseThrow(()->new AppException(ErrorCode.MONHOC_NOT_FOUND));
        Giangvien giangvien=giangvienRepo.findById(request.getMaCb())
                .orElseThrow(()->new AppException(ErrorCode.GIANGVIEN_NOT_FOUND));
        Lop lop=lopRepo.findById(request.getMaLop())
                .orElseThrow(()->new AppException(ErrorCode.LOP_NOT_FOUND));
        Phongmay phongmay=phongmayRepo.findById(request.getMaPm())
                .orElseThrow(()->new AppException(ErrorCode.PHONGMAY_NOT_FOUND));
        Dangkybu Dangkybu= DangkybuMapper.toDangkybu(request);
        Dangkybu.setLop(lop);
        Dangkybu.setGiangvien(giangvien);
        Dangkybu.setMonhoc(monhoc);
        Dangkybu.setPhongmay(phongmay);
        return DangkybuMapper.toDangkybuResponse(
                DangkybuRepo.save(Dangkybu));
    }
    public DangkybuResponse update(String id, DangkybuUpdate request){
        Dangkybu Dangkybu= DangkybuRepo.findById(id)
               .orElseThrow(() -> new AppException(ErrorCode.DANGKYBU_NOT_FOUND));
        DangkybuMapper.updateDangkybu(Dangkybu,request);
        return DangkybuMapper.toDangkybuResponse(
                DangkybuRepo.save(Dangkybu));
    }
    public void delete(String id){
        DangkybuRepo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DANGKYBU_NOT_FOUND));
        DangkybuRepo.deleteById(id);
    }

}
