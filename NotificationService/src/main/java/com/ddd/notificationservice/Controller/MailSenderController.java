package com.ddd.notificationservice.Controller;

import com.ddd.notificationservice.Dto.ApiResponse;
import com.ddd.notificationservice.Entity.NotificationApprove;
import com.ddd.notificationservice.Entity.NotificationReject;
import com.ddd.notificationservice.Service.JavaMailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class MailSenderController {
    JavaMailService javaMailService;
    @PostMapping("/reject")
    public ApiResponse<String> sendEmailText(@RequestBody NotificationReject mail){
        return ApiResponse.<String>builder()
                .code(0)
                .Result(javaMailService.javasendMailReject(mail))
                .success(true)
                .message("Success")
                .build();
    }
    @PostMapping("/approve")
    public ApiResponse<String> sendEmailHtml(@RequestBody NotificationApprove mail){
        return ApiResponse.<String>builder()
                .code(0)
                .Result(javaMailService.javasendMailApprove(mail))
                .success(true)
                .message("Success")
                .build();
    }
}
