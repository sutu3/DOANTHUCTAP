package com.example.serviceteacschedule_and_makeup_schedule_service.Entity;

import java.time.LocalDateTime;
import java.util.List;

import com.example.serviceteacschedule_and_makeup_schedule_service.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // ✅ Đảm bảo Hibernate hiểu rõ
    int userId;

    @Column(columnDefinition = "varchar(255) COMMENT 'tên người dùng'",nullable = false)
    String username;

    @Column(columnDefinition = "varchar(255) COMMENT 'mật khẩu'",nullable = false)
    String password;


    @Column(columnDefinition = "varchar(255) COMMENT 'ho và ten day du'",nullable = false)
    String fullname;

    @Column(columnDefinition = "varchar(255) COMMENT 'tên ca'",nullable = false,unique = true)
    String email;
    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL,orphanRemoval = true)
    List<MakeupRequest> makeupRequests;
    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Classes> classes;
    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Notification> notifications;

    @Enumerated(EnumType.STRING)
    Role role;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @UpdateTimestamp
    LocalDateTime updatedAt;
}

