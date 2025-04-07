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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class AuthenService {
    private final AuthApi authApi;

    public void authenAdmin(TokenRequest token){
        UserAuthResponse user;
        try {
            user = authApi.validate(token); // Call to the Auth API
            if (user == null || user.getRole() == null) {
                throw new AppException(ErrorCode.UNAUTHENTICATION);
            }

            // Log role for debugging
            log.info("User role: {}", user.getRole());

            if (!user.getRole().name().equals(Role.ADMIN.name())) {
                throw new AppException(ErrorCode.UNAUTHENTICATION);
            }
        } catch (Exception e) {
            log.error("Error while authenticating giangvien", e);
            throw new AppException(ErrorCode.UNAUTHENTICATION);
        }
    }
    public UserAuthResponse GetIdentity(TokenRequest token){
        UserAuthResponse user;
        try {
            user = authApi.validate(token); // Call to the Auth API
            if (user == null || user.getRole() == null) {
                throw new AppException(ErrorCode.UNAUTHENTICATION);
            }
                return user;
            // Log role for debugging

        } catch (Exception e) {
            log.error("Error while authenticating giangvien", e);
            throw new AppException(ErrorCode.UNAUTHENTICATION);
        }
    }
    public void authenGiangvien(TokenRequest token){
        UserAuthResponse user;
        try {
            user = authApi.validate(token); // Call to the Auth API
            if (user == null || user.getRole() == null) {
                throw new AppException(ErrorCode.UNAUTHENTICATION);
            }

            // Log role for debugging
            log.info("User role: {}", user.getRole());

            if (!user.getRole().name().equals(Role.GIANGVIEN.name())) {
                throw new AppException(ErrorCode.UNAUTHENTICATION);
            }
        } catch (Exception e) {
            log.error("Error while authenticating giangvien", e);
            throw new AppException(ErrorCode.UNAUTHENTICATION);
        }
    }
}
