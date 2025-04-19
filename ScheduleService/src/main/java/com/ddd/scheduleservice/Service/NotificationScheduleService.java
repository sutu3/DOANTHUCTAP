package com.ddd.scheduleservice.Service;

import com.ddd.scheduleservice.Dto.Request.NotificationRequest;
import com.ddd.scheduleservice.Dto.Response.NotificationResponse;
import com.ddd.scheduleservice.Entity.Notification;
import com.ddd.scheduleservice.Entity.User;
import com.ddd.scheduleservice.Enum.ReadStatus;
import com.ddd.scheduleservice.Exception.AppException;
import com.ddd.scheduleservice.Exception.ErrorCode;
import com.ddd.scheduleservice.Mapper.NotificationMapper;
import com.ddd.scheduleservice.Repo.NotificationRepo;
import com.ddd.scheduleservice.Repo.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class NotificationScheduleService {

    NotificationRepo notificationRepo;
    UserRepo userRepo;
    NotificationMapper notificationMapper;
    KafkaTemplate<String,Object> kafkaTemplate;
    public void sendMessage(NotificationResponse notificationResponse) throws ExecutionException, InterruptedException, TimeoutException {
        kafkaTemplate.send("notification",notificationResponse).thenAcceptAsync(stringObjectSendResult -> {
            Notification notification=notificationRepo.getById(notificationResponse.getNotificationId());
            notification.setReadStatus(ReadStatus.READ);
            notification.setNotificationTime(LocalDateTime.now());
            notificationRepo.save(notification);
        }).thenAcceptAsync(unused -> {
            Notification notification=notificationRepo.getById(notificationResponse.getNotificationId());
            notification.setReadStatus(ReadStatus.UNREAD);
            notification.setNotificationTime(LocalDateTime.now());
            notificationRepo.save(notification);
            log.info("Không gửi được message với tin nhắn : "+notificationResponse.getMessage()+" cho người dùng "
                    +notificationResponse.getUser().getEmail());
        });
    }
    public Optional<Notification> getNotificationById(String id) {
        return notificationRepo.findById(id);
    }


  
    public NotificationResponse createNotification(NotificationRequest request) {
        User user=userRepo.findByEmail(request.getEmail_user());
        if(user==null){
            throw new AppException(ErrorCode.GIANGVIEN_NOT_FOUND);
        }

        return notificationMapper.toNotificationResponse(notificationRepo.save(Notification.builder()
                .user(user)
                .message(request.getMessage())
                .build()));
    }

   
    public Notification updateNotification(String id, Notification notificationDetails) {
        return notificationRepo.findById(id).map(existingNotification -> {
            existingNotification.setUser(notificationDetails.getUser());
            existingNotification.setMessage(notificationDetails.getMessage());
            existingNotification.setReadStatus(notificationDetails.getReadStatus());
            return notificationRepo.save(existingNotification);
        }).orElseThrow(() -> new RuntimeException("Không tìm thấy thông báo với ID: " + id));
    }

    
    public void deleteNotification(String id) {
        notificationRepo.deleteById(id);
    }
}