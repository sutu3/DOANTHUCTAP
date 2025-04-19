package com.ddd.notificationservice.Service;

import com.ddd.notificationservice.Entity.NotificationMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageService {
    JavaMailService javaMailService;

    @KafkaListener(groupId = "notificationGroup", topics = "notification")
    public void listen(@Payload NotificationMessage message) {
        try {
            System.out.println("📩 Received raw message: " + message);
            javaMailService.javasendMailMessage(message);
            System.out.println("✅ Email sent successfully!");
        } catch (Exception e) {
            System.err.println("❌ Error processing message: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
