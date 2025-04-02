package com.example.serviceteacschedule_and_makeup_schedule_service.Service;

import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Notification;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;


    public List<Notification> getAllNotifications() {
        return notificationRepo.findAll();
    }


    public Optional<Notification> getNotificationById(int id) {
        return notificationRepo.findById(id);
    }

  
    public Notification createNotification(Notification notification) {
        return notificationRepo.save(notification);
    }

   
    public Notification updateNotification(int id, Notification notificationDetails) {
        return notificationRepo.findById(id).map(existingNotification -> {
            existingNotification.setUser(notificationDetails.getUser());
            existingNotification.setMessage(notificationDetails.getMessage());
            existingNotification.setReadStatus(notificationDetails.getReadStatus());
            return notificationRepo.save(existingNotification);
        }).orElseThrow(() -> new RuntimeException("Không tìm thấy thông báo với ID: " + id));
    }

    
    public void deleteNotification(int id) {
        notificationRepo.deleteById(id);
    }
}