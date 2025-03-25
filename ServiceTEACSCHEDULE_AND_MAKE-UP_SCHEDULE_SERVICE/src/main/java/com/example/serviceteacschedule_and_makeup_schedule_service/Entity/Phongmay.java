package com.example.serviceteacschedule_and_makeup_schedule_service.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
public class Phongmay {
    @Id
    @Column(columnDefinition = "char(10) COMMENT 'mã phòng học'",nullable = false,unique = true)
    String maPm;
    @OneToMany(mappedBy = "phongmay",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Dangkybu> dangkybu;
    @OneToMany(mappedBy = "phongmay",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Lichgiangday> lichgiangday;
    @Column(columnDefinition = "varchar(255) COMMENT 'tên phòng học'",nullable = false)
    String tenPm;
    @Column(columnDefinition = "INT COMMENT 'Sức chứa'", nullable = false)
    int sucChua;
}
