package com.example.serviceteacschedule_and_makeup_schedule_service.Repo;

import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Giangvien;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Monhoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonhocRepo extends JpaRepository<Monhoc,String> {
}
