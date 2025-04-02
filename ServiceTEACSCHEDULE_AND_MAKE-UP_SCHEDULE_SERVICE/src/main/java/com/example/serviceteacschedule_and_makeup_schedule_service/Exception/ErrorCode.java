package com.example.serviceteacschedule_and_makeup_schedule_service.Exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@NoArgsConstructor
public enum ErrorCode {
    INVALID_KEY(1001,"Invalid key",HttpStatus.BAD_REQUEST),
    GIANGVIEN_NOT_FOUND(1002,"Khong tim thay giang vien",HttpStatus.NOT_FOUND),
    LOP_NOT_FOUND(1003,"Khong tim thay lop",HttpStatus.NOT_FOUND),
    MONHOC_NOT_FOUND(1004,"Khong tim thay Mon hoc",HttpStatus.NOT_FOUND),
    DANGKYBU_NOT_FOUND(1004,"Khong tim thay ma dang ky bu",HttpStatus.NOT_FOUND),
    PHONGMAY_NOT_FOUND(1004,"Khong tim thay Phong may",HttpStatus.NOT_FOUND),
    CA_NOT_FOUND(1004,"Khong tim thay Ca hoc",HttpStatus.NOT_FOUND),
    LICHGIANGDAY_NOT_FOUND(1004,"Khong tim thay lich giang day",HttpStatus.NOT_FOUND),
    UNCATEGORIZED(9999,"Uncategorized", HttpStatus.INTERNAL_SERVER_ERROR);
    ErrorCode(int Code,String Message, HttpStatusCode sponse){
        this.code = Code;
        this.message = Message;
        this.status = sponse;
    }
    private int code;
    private String message;
    private HttpStatusCode status;
}
