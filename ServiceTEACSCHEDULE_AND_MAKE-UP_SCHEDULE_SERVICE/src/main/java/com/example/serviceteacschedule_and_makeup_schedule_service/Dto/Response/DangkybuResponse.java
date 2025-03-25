package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response;

import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Giangvien;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Lop;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Monhoc;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Phongmay;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DangkybuResponse {
    String maDangKy;
    int siSo;
    Monhoc monhoc;
    Giangvien giangvien;
    Lop lop;
    Phongmay phongmay;
    String tietHoc;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date ngayDayBu;
}
