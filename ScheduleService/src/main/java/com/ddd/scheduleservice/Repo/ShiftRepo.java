package com.ddd.scheduleservice.Repo;

import com.ddd.scheduleservice.Entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepo extends JpaRepository<Shift, Integer> {
    
}