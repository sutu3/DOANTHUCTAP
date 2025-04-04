package com.example.serviceteacschedule_and_makeup_schedule_service.Repo;

import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Classes;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepo extends JpaRepository<Classes, Integer> {
  Classes findFirstBySubject_SubjectIdAndRoom_RoomIdAndUser_UserId(int subject_subjectId, int room_roomId, int user_userId);
}