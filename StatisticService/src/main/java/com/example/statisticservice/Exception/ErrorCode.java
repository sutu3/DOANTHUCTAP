package com.example.statisticservice.Exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@NoArgsConstructor
public enum ErrorCode {
    NOT_FOUND(404, "Not Found", HttpStatus.NOT_FOUND),
    BAD_REQUEST(400, "Bad Request", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNCATEGORIZED(9999,"Uncategorized",HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(9998,"You don't as permission" ,HttpStatus.FORBIDDEN );
    ErrorCode(int Code,String Message, HttpStatusCode sponse){
        this.code = Code;
        this.message = Message;
        this.status = sponse;
    }
    private int code;
    private String message;
    private HttpStatusCode status;
}
