package com.ddd.scheduleservice.Service;

import com.ddd.scheduleservice.Client.AuthApi;
import com.ddd.scheduleservice.Client.FileService;
import com.ddd.scheduleservice.Dto.Request.TokenRequest;
import com.ddd.scheduleservice.Dto.Request.UserRequest;
import com.ddd.scheduleservice.Dto.Request.UserRequestAuthenService;
import com.ddd.scheduleservice.Dto.Response.ImageResponse;
import com.ddd.scheduleservice.Dto.Response.UserAuthResponse;
import com.ddd.scheduleservice.Dto.Response.UserResponse;
import com.ddd.scheduleservice.Entity.User;
import com.ddd.scheduleservice.Enum.Role;
import com.ddd.scheduleservice.Exception.AppException;
import com.ddd.scheduleservice.Exception.ErrorCode;
import com.ddd.scheduleservice.Form.UserUpdate;
import com.ddd.scheduleservice.Mapper.UserMapper;
import com.ddd.scheduleservice.Repo.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class UserService {
    UserMapper userMapper;

    UserRepo userRepo;
    FileService fileService;
    AuthenService authenService;
    AuthApi authApi;


    public List<UserResponse> getAllUsers() {
        return userRepo.findAll().stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    
    public UserResponse getUserById(int id) {
        return userMapper.toUserResponse(
                userRepo.findById(id).orElseThrow(()->
                        new AppException(ErrorCode.GIANGVIEN_NOT_FOUND)));
    }

    @PostAuthorize("returnObject.username==authentication.name")
    public UserResponse getUserByToken(String token) throws AppException{
        var context= SecurityContextHolder.getContext();
        String userName=context.getAuthentication().getName();
        return userMapper.toUserResponse(userRepo.findByUsername(userName));

    }
    
    public UserResponse getUserByUsername(String username) {
        return userMapper.toUserResponse(
                userRepo.findByUsername(username));
    }
    public UserResponse UpdateRoleAdmin(int Userid) {

        /*authenService.authenAdmin(TokenRequest.builder()
                .Token(token)
                .build());
        if(userRepo.findByEmail(request.getEmail())!=null){
            throw new AppException(ErrorCode.EMAIL_IS_EXIST);
        }*/

        User user=userRepo.findById(Userid).orElseThrow(()->new AppException(ErrorCode.GIANGVIEN_NOT_FOUND));

        return userMapper.toUserResponse(
                userRepo.save(user));
    }


    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse createUser(UserRequest request,MultipartFile file,String token) {

        if(userRepo.findByEmail(request.getEmail())!=null){
            throw new AppException(ErrorCode.EMAIL_IS_EXIST);
        }
        authApi.createdUser(UserRequestAuthenService.builder()
                        .userName(request.getUsername())
                        .email(request.getEmail())
                        .phoneNumber(request.getPhoneNumber())
                        .password(request.getPassword())
                .build(), "Bearer " + token);

        ImageResponse image= fileService.uploadFileImage(file);
        User user =userMapper.toUser(request);
        user.setImageUrl(image.getUrlImage());
        return userMapper.toUserResponse(
                userRepo.save(user));
    }

   
    public UserResponse updateUser(int id, UserUpdate userDetails) {
        User user =userRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.GIANGVIEN_NOT_FOUND));
        userMapper.updateUser(user,userDetails);
        return userMapper.toUserResponse(
                userRepo.save(user));
    }

   
    public void deleteUser(int id,String token) {
        authenService.authenAdmin(TokenRequest.builder()
                .token(token)
                .build());
        userRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.GIANGVIEN_NOT_FOUND));
        userRepo.deleteById(id);
    }
}