package com.example.serviceteacschedule_and_makeup_schedule_service.Client;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.NotificationApprove;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.NotificationReject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "NotificationService",url = "http://localhost:9083")
public interface NotificationService {
    @PostMapping("/mail/reject")
    void sendMailReject(NotificationReject notificationReject);
    @PostMapping("/mail/approve")
    void sendMailApprove(NotificationApprove notificationApprove);
}
