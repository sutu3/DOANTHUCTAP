package com.ddd.scheduleservice.Client;

import com.ddd.scheduleservice.Client.Impl.NotificationServiceImpl;
import com.ddd.scheduleservice.Dto.Request.NotificationApprove;
import com.ddd.scheduleservice.Dto.Request.NotificationReject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "NotificationService",fallback = NotificationServiceImpl.class)
public interface NotificationService {
    @PostMapping("/mail/reject")
    void sendMailReject(NotificationReject notificationReject);
    @PostMapping("/mail/approve")
    void sendMailApprove(NotificationApprove notificationApprove);
}
