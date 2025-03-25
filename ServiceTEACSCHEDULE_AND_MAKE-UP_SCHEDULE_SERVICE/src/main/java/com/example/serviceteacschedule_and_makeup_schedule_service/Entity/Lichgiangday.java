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
public class Lichgiangday {
    @Id
    @Column(columnDefinition = "char(10) COMMENT 'ma lich'",nullable = false,unique = true)
    String maLich;
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
    @Column(columnDefinition = "INT COMMENT 'nhom'", nullable = false)
    int NHOM;
    @Column(columnDefinition = "char(10) COMMENT 'to thuc hanh'",nullable = false)
    String toTh;
    @Column(columnDefinition = "INT COMMENT 'si so'", nullable = false)
    int SISO;
    @Column(columnDefinition = "INT COMMENT 'thu'", nullable = false)
    int THU;
    @Column(columnDefinition = "char(10) COMMENT 'tiet hoc'",nullable = false)
    String TIETHOC;
    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "date COMMENT 'thoi gian bat dau'",nullable = false)
    Date thoiGianHoc_BD;
    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "date COMMENT 'thoi gian ket thuc'",nullable = false)
    Date thoiGianHoc_KT;
    @Column(columnDefinition = "INT COMMENT 'hoc ky'", nullable = false)
    int hocKy;
    @Column(columnDefinition = "char(10) COMMENT 'nam hoc'", nullable = false)
    String namHoc;
    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "date COMMENT 'thoi gian bat dau hoc ky'",nullable = false)
    Date ngayBdHk;
    @Column(columnDefinition = "INT COMMENT 'so tuan trong hoc ky'", nullable = false)
    int tuan;
}
