package com.ddd.scheduleservice.Service;

import com.ddd.scheduleservice.Client.AuthApi;
import com.ddd.scheduleservice.Client.FileService;
import com.ddd.scheduleservice.Dto.Request.TokenRequest;
import com.ddd.scheduleservice.Dto.Request.UserRequest;
import com.ddd.scheduleservice.Dto.Response.ImageResponse;
import com.ddd.scheduleservice.Dto.Response.UserAuthResponse;
import com.ddd.scheduleservice.Dto.Response.UserResponse;
import com.ddd.scheduleservice.Entity.User;
import com.ddd.scheduleservice.Enum.Role;
import com.ddd.scheduleservice.Enum.RoleUser;
import com.ddd.scheduleservice.Exception.AppException;
import com.ddd.scheduleservice.Exception.ErrorCode;
import com.ddd.scheduleservice.Form.UserUpdate;
import com.ddd.scheduleservice.Mapper.UserMapper;
import com.ddd.scheduleservice.Repo.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.io.HttpServerRequestHandler;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private final AuthenService authenService;


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

    public UserResponse getUserByToken(String token) throws AppException{
        UserAuthResponse userAuthResponse = authenService.GetIdentity(TokenRequest.builder()
               .Token(token)
               .build());
        return userMapper.toUserResponse(
                userRepo.findByEmail(userAuthResponse.getEmail()));
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
        user.setRole(Role.ADMIN);
        return userMapper.toUserResponse(
                userRepo.save(user));
    }

  
    public UserResponse createUser(UserRequest request,MultipartFile file,String token) {

        /*authenService.authenAdmin(TokenRequest.builder()
                .Token(token)
                .build());
        if(userRepo.findByEmail(request.getEmail())!=null){
            throw new AppException(ErrorCode.EMAIL_IS_EXIST);
        }*/
        ImageResponse image= fileService.uploadFileImage(file);
        User user =userMapper.toUser(request);
        user.setRole(Role.GIANGVIEN);
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
                .Token(token)
                .build());
        userRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.GIANGVIEN_NOT_FOUND));
        userRepo.deleteById(id);
    }
}