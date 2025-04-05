package com.example.serviceteacschedule_and_makeup_schedule_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ServiceTeacscheduleAndMakeUpScheduleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTeacscheduleAndMakeUpScheduleServiceApplication.class, args);
    }
}
