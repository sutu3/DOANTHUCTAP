package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DangkybuRequest {
    int siSo;
    String toTh;
    String maMh;
    String maCb;
    String maLop;
    String maPm;
    String tietHoc;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date ngayDayBu;
}
