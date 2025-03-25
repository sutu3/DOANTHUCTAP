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
public class Giangvien {
    @Id
    @Column(columnDefinition = "char(10) COMMENT 'ma can bo'",nullable = false,unique = true)
    String msCb;
    @Column(columnDefinition = "varchar(255) COMMENT 'ten can bo'",nullable = false)
    String tenCb;
    @OneToMany(mappedBy ="giangvien",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Dangkybu> dangkybu;
    @OneToMany(mappedBy ="giangvien",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Lichgiangday> lichgiangday;
}
