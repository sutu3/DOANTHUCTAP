package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response;

import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Giangvien;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Lop;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Monhoc;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Phongmay;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LichgiangdayResponse {
    String maLich;

    Monhoc monhoc;
    Giangvien giangvien;
    Lop lop;
    Phongmay phongmay;
    int nhom;
    String toTh;
    int siSo;
    int thu;
    String tietHoc;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date thoiGianHoc_BD;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date thoiGianHoc_KT;
    int hocKy;
    String namHoc;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date ngayBdHk;
    int tuan;
}
