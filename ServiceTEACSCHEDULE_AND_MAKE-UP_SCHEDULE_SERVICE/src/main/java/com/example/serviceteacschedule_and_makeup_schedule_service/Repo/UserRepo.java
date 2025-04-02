package com.example.serviceteacschedule_and_makeup_schedule_service.Repo;

import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}