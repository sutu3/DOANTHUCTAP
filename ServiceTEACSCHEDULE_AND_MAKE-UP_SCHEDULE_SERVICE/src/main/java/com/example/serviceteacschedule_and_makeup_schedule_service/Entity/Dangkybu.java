package com.example.serviceteacschedule_and_makeup_schedule_service.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
public class Dangkybu {
    @Id
    @Column(columnDefinition = "char(10) COMMENT 'ma dang ky bu'",nullable = false,unique = true)
    String maDangKy;
    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @Column(name = "maMh",nullable = false)
    Monhoc monhoc;
    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @Column(name = "maCb",nullable = false)
    Giangvien giangvien;
    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @Column(name = "maLop",nullable = false)
    Lop lop;
    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @Column(name = "maPm",nullable = false)
    Phongmay phongmay;
    @Column(columnDefinition = "INT COMMENT 'si so'", nullable = false)
    int siSo;
    @Column(columnDefinition = "char(15) COMMENT 'tiet hoc'",nullable = false)
    String tietHoc;
    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "date COMMENT 'ngay dang ky bu'",nullable = false)
    Date ngayDayBu;
}
