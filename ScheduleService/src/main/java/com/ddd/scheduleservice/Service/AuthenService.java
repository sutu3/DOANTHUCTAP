package com.ddd.scheduleservice.Service;

import com.ddd.scheduleservice.Client.AuthApi;
import com.ddd.scheduleservice.Dto.Request.TokenRequest;
import com.ddd.scheduleservice.Dto.Response.UserAuthResponse;
import com.ddd.scheduleservice.Enum.Role;
import com.ddd.scheduleservice.Exception.AppException;
import com.ddd.scheduleservice.Exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class AuthenService {
    private final AuthApi authApi;
    @PreAuthorize("hasRole('ADMIN')")
    public void authenAdmin(TokenRequest token){
        try {
            boolean introspectResponse = authApi.introspect(token).getResult().isValid(); // Call to the Auth API
            if (!introspectResponse) {
                throw new AppException(ErrorCode.UNAUTHENTICATED);
            }
        } catch (Exception e) {
            log.error("Error while authenticating giangvien", e);
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }
    @PreAuthorize("hasRole('TEACHER')")
    public void authenTeacher(TokenRequest token){

        try {
            boolean introspectResponse = authApi.introspect(token).getResult().isValid(); // Call to the Auth API
            if (!introspectResponse) {
                throw new AppException(ErrorCode.UNAUTHENTICATED);
            }
        } catch (Exception e) {
            log.error("Error while authenticating giangvien", e);
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }
    public UserAuthResponse GetIdentity(TokenRequest token){
        UserAuthResponse user;
        try {
            user = authApi.validate(token); // Call to the Auth API
            if (user == null || user.getRole() == null) {
                throw new AppException(ErrorCode.UNAUTHENTICATED);
            }
                return user;
            // Log role for debugging

        } catch (Exception e) {
            log.error("Error while authenticating giangvien", e);
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }
    public void authenGiangvien(TokenRequest token){
        UserAuthResponse user;
        try {
            user = authApi.validate(token); // Call to the Auth API
            if (user == null || user.getRole() == null) {
                throw new AppException(ErrorCode.UNAUTHENTICATED);
            }

            // Log role for debugging
            log.info("User role: {}", user.getRole());

            if (!user.getRole().name().equals(Role.Teacher.name())) {
                throw new AppException(ErrorCode.UNAUTHENTICATED);
            }
        } catch (Exception e) {
            log.error("Error while authenticating giangvien", e);
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }
}
