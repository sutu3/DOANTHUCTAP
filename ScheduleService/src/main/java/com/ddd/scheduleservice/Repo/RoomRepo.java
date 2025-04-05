package com.ddd.scheduleservice.Repo;

import com.ddd.scheduleservice.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {
   
}