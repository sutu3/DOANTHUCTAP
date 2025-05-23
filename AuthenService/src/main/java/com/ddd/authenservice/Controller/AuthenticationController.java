package com.ddd.authenservice.Controller;

import com.ddd.authenservice.Dto.Request.AuthenticationRequest;
import com.ddd.authenservice.Dto.Request.IntrospectRequest;
import com.ddd.authenservice.Dto.Request.LogOutRequest;
import com.ddd.authenservice.Dto.Request.RefreshRequest;
import com.ddd.authenservice.Dto.Response.ApiResponse;
import com.ddd.authenservice.Dto.Response.AuthenticationResponse;
import com.ddd.authenservice.Dto.Response.IntrospectResponse;
import com.ddd.authenservice.Service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/authentication")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@CrossOrigin(origins = {"http://localhost:5173","http://localhost:5174","http://26.144.191.229:5173","http://26.225.63.179:5173"})
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request)
            throws  JOSEException {
        var result=authenticationService.isAuthenticated(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .Result(result)
                .success(true)
                .code(0)
                .build();
    }
    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authentication(@RequestBody IntrospectRequest request)
            throws  JOSEException, ParseException {
        var result=authenticationService.instrospect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .Result(result)
                .message("Completed")
                .success(true)
                .code(0)
                .build();
    }
    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogOutRequest request)
            throws  JOSEException, ParseException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .message("LogOut Completed")
                .success(true)
                .code(0)
                .build();
    }
    @PostMapping("/refresh")
    public ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request)
            throws  JOSEException, ParseException {
        var result=authenticationService.refresh(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .message("LogOut Completed")
                .Result(result)
                .success(true)
                .code(0)
                .build();
    }
}
