package com.ddd.scheduleservice.Client;

import com.ddd.scheduleservice.Client.Impl.AuthApiImpl;
import com.ddd.scheduleservice.Dto.Request.TokenRequest;
import com.ddd.scheduleservice.Dto.Response.UserAuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AuthApi",url = "http://localhost:5125",fallback = AuthApiImpl.class)
public interface AuthApi {
    @PostMapping(value = "/api/Auth/Validate")
    UserAuthResponse validate(@RequestBody TokenRequest token);
    }
