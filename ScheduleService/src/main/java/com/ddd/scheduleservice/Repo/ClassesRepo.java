package com.ddd.scheduleservice.Repo;

import com.ddd.scheduleservice.Entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepo extends JpaRepository<Classes, Integer> {
  Classes findFirstBySubject_SubjectIdAndRoom_RoomIdAndUser_UserId(int subject_subjectId, int room_roomId, int user_userId);
}