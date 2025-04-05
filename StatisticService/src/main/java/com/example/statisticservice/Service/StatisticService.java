package com.example.statisticservice.Service;

import com.example.statisticservice.Dto.Request.StatisticRequest;
import com.example.statisticservice.Dto.Response.ApiResponse;
import com.example.statisticservice.Entity.Statistic;
import com.example.statisticservice.Mapper.StatisticMapper;
import com.example.statisticservice.Repository.StatisticRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class StatisticService {
    StatisticRepo statisticRepo;
    StatisticMapper statisticMapper;
    public ApiResponse<Statistic> add(StatisticRequest StatisticRequest){
        Statistic Statistic = statisticMapper.toStatistic(StatisticRequest);
        Statistic.setCreatedDate(LocalDateTime.now());
        return ApiResponse.<Statistic>builder()
                .message("Successfully")
                .Result(statisticRepo.save(Statistic))
                .success(true)
                .code(0).build();
    }
    public ApiResponse<Statistic> getById(String StatisticId){
        return ApiResponse.<Statistic>builder()
                .message("Successfully")
                .Result(statisticRepo.findById(StatisticId)
                        .orElseThrow(() -> new RuntimeException("Statistic not found")))
                .success(true)
                .code(0).build();
    }
    /*public ApiResponse<Statistic> updateStatistic(StatisticRequest StatisticRequest){
    Statistic Statistic = statisticRepo.findById(StatisticRequest.getId())
                .orElseThrow(() -> new RuntimeException("Statistic not found"));
    statisticMapper.toUpdate(Statistic, StatisticRequest);
    return ApiResponse.<Statistic>builder()
            .message("Successfully")
            .Result(statisticRepo.save(Statistic))
            .success(true)
            .code(0).build();
    }*/
    public void delete(String StatisticId){
        statisticRepo.findById(StatisticId)
                .orElseThrow(() -> new RuntimeException("Statistic not found"));
        statisticRepo.deleteById(StatisticId);
    }
    public ApiResponse<List<Statistic>>  getall(){
        return ApiResponse.<List<Statistic>>builder()
                .message("Successfully")
                .Result(statisticRepo.findAll())
                .success(true)
                .code(0).build();
    }
}
