package com.example.serviceteacschedule_and_makeup_schedule_service.Form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LichgiangdayUpdate {
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
