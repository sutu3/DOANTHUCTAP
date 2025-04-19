package com.ddd.notificationservice.Service;

import com.ddd.notificationservice.Entity.NotificationApprove;
import com.ddd.notificationservice.Entity.NotificationMessage;
import com.ddd.notificationservice.Entity.NotificationReject;
import com.ddd.notificationservice.Service.Impl.JavaMailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class JavaMailService implements JavaMailSender {
    String MAIL_HOST="minhdaimk111@gmail.com";
    org.springframework.mail.javamail.JavaMailSender javaMailSender;
    SpringTemplateEngine templateEngine;
    @Override
    public String javasendMailReject(NotificationReject messageMail) {
        System.out.println("🚀 Sending email to: " + messageMail.getTo());
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariable("name", messageMail.getToName());
            context.setVariable("id",messageMail.getId());
            context.setVariable("subject",messageMail.getSubject());
            context.setVariable("room",messageMail.getRoom());
            context.setVariable("content", messageMail.getContent());
            String html = templateEngine.process("rejectmail", context);

            helper.setTo(messageMail.getTo());
            helper.setText(html, true);  // <--- Đảm bảo nội dung email đúng
            helper.setSubject(messageMail.getMessage());
            helper.setFrom("minhdaimk111@gmail.com");

            javaMailSender.send(message);
            return new String("✅ Email sent successfully!");
        } catch (MessagingException e) {
            return new String("❌ Email sending failed: " + e.getMessage());
        }
    }

    @Override
    public String javasendMailApprove(NotificationApprove messageMail) {
        System.out.println("🚀 Sending email to: " + messageMail.getTo());
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariable("name", messageMail.getToName());
            context.setVariable("content", messageMail.getContent());
            context.setVariable("class", messageMail.getClassification());
            context.setVariable("subject", messageMail.getSubject());
            context.setVariable("date", messageMail.getDate());
            context.setVariable("shift", messageMail.getShift());
            String html = templateEngine.process("approvemail", context);

            helper.setTo(messageMail.getTo());
            helper.setText(html, true);  // <--- Đảm bảo nội dung email đúng
            helper.setSubject(messageMail.getMessage());
            helper.setFrom("minhdaimk111@gmail.com");

            javaMailSender.send(message);
            return new String("✅ Email sent successfully!");
        } catch (MessagingException e) {
            return new String("❌ Email sending failed: " + e.getMessage());
        }
    }

    @Override
    public String javasendMailMessage(NotificationMessage messageMail) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariable("name", messageMail.getUser().getFullname());
            context.setVariable("message", messageMail.getMessage());
            String html = templateEngine.process("messageemail", context);

            helper.setTo(messageMail.getUser().getEmail());
            helper.setText(html, true);  // <--- Đảm bảo nội dung email đúng
            helper.setSubject("Tin nhắn từ lịch giảng dạy");
            helper.setFrom("minhdaimk111@gmail.com");

            javaMailSender.send(message);
            return new String("✅ Email sent successfully!");
        } catch (MessagingException e) {
            return new String("❌ Email sending failed: " + e.getMessage());
        }
    }
}
