package com.example.serviceteacschedule_and_makeup_schedule_service.Service;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.LichgiangdayRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.LichgiangdayResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.*;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.AppException;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.ErrorCode;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.LichgiangdayUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Mapper.LichgiangdayMapper;
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
public class LichgiangdayService {
    LichgiangdayRepo LichgiangdayRepo;
    LichgiangdayMapper LichgiangdayMapper;
    private final MonhocRepo monhocRepo;
    private final GiangvienRepo giangvienRepo;
    private final LopRepo lopRepo;
    private final PhongmayRepo phongmayRepo;

    public LichgiangdayResponse getbyId(String id){
        return LichgiangdayMapper.toLichgiangdayResponse(
                LichgiangdayRepo.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.LICHGIANGDAY_NOT_FOUND)));
    }
    public List<LichgiangdayResponse> getall(){
        return LichgiangdayRepo.findAll().stream().map(LichgiangdayMapper::toLichgiangdayResponse)
                .collect(Collectors.toList());
    }
    public LichgiangdayResponse create(LichgiangdayRequest request){
        Monhoc monhoc=monhocRepo.findById(request.getMaMh())
                .orElseThrow(()->new AppException(ErrorCode.MONHOC_NOT_FOUND));
        Giangvien giangvien=giangvienRepo.findById(request.getMaCb())
                .orElseThrow(()->new AppException(ErrorCode.GIANGVIEN_NOT_FOUND));
        Lop lop=lopRepo.findById(request.getMaLop())
                .orElseThrow(()->new AppException(ErrorCode.LOP_NOT_FOUND));
        Phongmay phongmay=phongmayRepo.findById(request.getMaPm())
                .orElseThrow(()->new AppException(ErrorCode.PHONGMAY_NOT_FOUND));
        Lichgiangday lichgiangday=LichgiangdayMapper.toLichgiangday(request);
        lichgiangday.setLop(lop);
        lichgiangday.setGiangvien(giangvien);
        lichgiangday.setMonhoc(monhoc);
        lichgiangday.setPhongmay(phongmay);

        return LichgiangdayMapper.toLichgiangdayResponse(
                LichgiangdayRepo.save(lichgiangday));
    }
    public LichgiangdayResponse update(String id, LichgiangdayUpdate request){
        Lichgiangday Lichgiangday= LichgiangdayRepo.findById(id)
               .orElseThrow(() -> new AppException(ErrorCode.LICHGIANGDAY_NOT_FOUND));
        LichgiangdayMapper.updateLichgiangday(Lichgiangday,request);
        return LichgiangdayMapper.toLichgiangdayResponse(
                LichgiangdayRepo.save(Lichgiangday));
    }
    public void delete(String id){
        LichgiangdayRepo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.LICHGIANGDAY_NOT_FOUND));
        LichgiangdayRepo.deleteById(id);
    }

}
