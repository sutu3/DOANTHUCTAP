package com.ddd.scheduleservice.Client.Impl;

import com.ddd.scheduleservice.Client.AuthApi;
import com.ddd.scheduleservice.Dto.Request.TokenRequest;
import com.ddd.scheduleservice.Dto.Response.UserAuthResponse;
import com.ddd.scheduleservice.Exception.AppException;
import com.ddd.scheduleservice.Exception.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class AuthApiImpl implements AuthApi {
    @Override
    public UserAuthResponse validate(TokenRequest token) {

         throw new AppException(ErrorCode.AUTHEN_SERVICE_NOTWORK);
    }
}
