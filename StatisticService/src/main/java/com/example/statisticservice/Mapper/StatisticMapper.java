package com.example.statisticservice.Mapper;


import com.example.statisticservice.Dto.Request.StatisticRequest;
import com.example.statisticservice.Entity.Statistic;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StatisticMapper {
    Statistic toStatistic(StatisticRequest request);
    void toUpdate(@MappingTarget Statistic statistic,StatisticRequest update);
}
