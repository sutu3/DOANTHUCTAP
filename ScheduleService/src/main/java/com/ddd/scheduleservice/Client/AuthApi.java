package com.ddd.scheduleservice.Client;

import com.ddd.scheduleservice.Client.Impl.AuthApiImpl;
import com.ddd.scheduleservice.Dto.Request.TokenRequest;
import com.ddd.scheduleservice.Dto.Request.UserRequestAuthenService;
import com.ddd.scheduleservice.Dto.Response.ApiResponse;
import com.ddd.scheduleservice.Dto.Response.IntrospectResponse;
import com.ddd.scheduleservice.Dto.Response.UserAuthResponse;
import com.ddd.scheduleservice.Dto.Response.UserResponseAuthenService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "AuthApi",url = "http://localhost:5125",fallback = AuthApiImpl.class)
public interface AuthApi {
    @PostMapping(value = "/api/Auth/Validate")
    UserAuthResponse validate(@RequestBody TokenRequest token);
    @PostMapping(value="/authentication/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody TokenRequest token);
    @PostMapping(value="/getinfor")
    ApiResponse<UserResponseAuthenService> getmyInfor(@RequestBody TokenRequest token);
    @PostMapping(value="/users")
    void createdUser(@RequestBody UserRequestAuthenService user, @RequestHeader("Authorization") String token);
    }
