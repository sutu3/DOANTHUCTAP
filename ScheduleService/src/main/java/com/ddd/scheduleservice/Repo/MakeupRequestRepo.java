package com.ddd.scheduleservice.Repo;

import com.ddd.scheduleservice.Entity.MakeupRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakeupRequestRepo extends JpaRepository<MakeupRequest, Integer> {
    
}