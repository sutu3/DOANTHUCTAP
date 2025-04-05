package com.ddd.notificationservice.Exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@NoArgsConstructor
public enum ErrorCode {
    INVALID_KEY(1001,"Invalid key",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1002,"Khong tim thay nguoi dung",HttpStatus.NOT_FOUND),
    FEED_NOT_FOUND(1003,"Khong tim thay feed",HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND(1004,"Khong tim thay product",HttpStatus.NOT_FOUND),
    ORDER_NOT_FOUND(1004,"Khong tim thay product",HttpStatus.NOT_FOUND),
    MAIL_NOT_SEND(9998,"Khong gui mail duoc",HttpStatus.BAD_REQUEST),
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
