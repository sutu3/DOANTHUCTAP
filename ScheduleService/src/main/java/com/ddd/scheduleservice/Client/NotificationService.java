package com.ddd.scheduleservice.Client;

import com.ddd.scheduleservice.Dto.Request.NotificationApprove;
import com.ddd.scheduleservice.Dto.Request.NotificationReject;
import com.ddd.scheduleservice.Exception.AppException;
import com.ddd.scheduleservice.Exception.ErrorCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "NotificationService",fallback = NotificationServiceImpl.class)
public interface NotificationService {
    @PostMapping("/mail/reject")
    void sendMailReject(NotificationReject notificationReject);
    @PostMapping("/mail/approve")
    void sendMailApprove(NotificationApprove notificationApprove);
}
