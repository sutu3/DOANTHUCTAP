package com.ddd.notificationservice.Service.Impl;

import com.ddd.notificationservice.Entity.NotificationApprove;
import com.ddd.notificationservice.Entity.NotificationMessage;
import com.ddd.notificationservice.Entity.NotificationReject;

public interface JavaMailSender {
    String javasendMailReject(NotificationReject messageMail);
    String javasendMailApprove(NotificationApprove messageMail);
    String javasendMailMessage(NotificationMessage messageMail);
}
