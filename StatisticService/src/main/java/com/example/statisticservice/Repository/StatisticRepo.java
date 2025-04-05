package com.example.statisticservice.Repository;

import com.example.statisticservice.Entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepo extends JpaRepository<Statistic,String> {
}
