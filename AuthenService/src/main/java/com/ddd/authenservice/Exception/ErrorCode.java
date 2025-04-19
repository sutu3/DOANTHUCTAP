package com.ddd.authenservice.Exception;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@NoArgsConstructor
public enum ErrorCode {

    GENDER_IS_EXITED(1003,"Gendering Is Exited",HttpStatus.BAD_REQUEST),
    GENDER_INVALID(1002,"Gender Invalid",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1001,"User Not Found", HttpStatus.NOT_FOUND),
    USERNAME_INVALID(1002,"Username Invalid",HttpStatus.BAD_REQUEST),
    USERNAME_IS_EXITED(1003,"Username Is Exited", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004,"Password Invalid", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1005,"Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNCATEGORIZED(9999,"Uncategorized",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001,"Invalid key",HttpStatus.BAD_REQUEST),
    PERMISSION_IS_EXITED(1003,"Permission Is Exited", HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_FOUND(1001,"Permission Not Found", HttpStatus.NOT_FOUND),
    ROLE_IS_EXITED(1003,"Role Is Exited", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(1001,"Role Not Found", HttpStatus.NOT_FOUND),
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
