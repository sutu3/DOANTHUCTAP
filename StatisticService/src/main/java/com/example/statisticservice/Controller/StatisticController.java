package com.example.statisticservice.Controller;


import com.example.statisticservice.Dto.Request.StatisticRequest;
import com.example.statisticservice.Dto.Response.ApiResponse;
import com.example.statisticservice.Entity.Statistic;
import com.example.statisticservice.Service.StatisticService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class StatisticController {
    StatisticService statisticService;
    @GetMapping("/getall")
    public ApiResponse<List<Statistic>> getAll() {
        return statisticService.getall();
    }
    @GetMapping("/{id}")
    public ApiResponse<Statistic> getById(@PathVariable String id) {
        return statisticService.getById(id);
    }
    @PostMapping
    public ApiResponse<Statistic> addStatistic(@RequestBody StatisticRequest StatisticRequest) {
        return statisticService.add(StatisticRequest);
    }
    /*@PutMapping("/{id}")
    public ApiResponse<Statistic> updateStatistic(@RequestBody StatisticRequest StatisticRequest) {
        return statisticService.updateStatistic(StatisticRequest);
    }*/
    @DeleteMapping("/{id}")
    public void deleteStatistic(@PathVariable String id) {
        statisticService.delete(id);
    }

}
