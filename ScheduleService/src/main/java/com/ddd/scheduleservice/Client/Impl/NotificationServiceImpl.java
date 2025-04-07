package com.ddd.scheduleservice.Client.Impl;

import com.ddd.scheduleservice.Client.NotificationService;
import com.ddd.scheduleservice.Dto.Request.NotificationApprove;
import com.ddd.scheduleservice.Dto.Request.NotificationReject;
import com.ddd.scheduleservice.Exception.AppException;
import com.ddd.scheduleservice.Exception.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void sendMailReject(NotificationReject notificationReject) {
        throw new AppException(ErrorCode.NOTIFICATION_SERVICE_NOTWORK);
    }

    @Override
    public void sendMailApprove(NotificationApprove notificationApprove) {
        throw new AppException(ErrorCode.NOTIFICATION_SERVICE_NOTWORK);
    }
}
