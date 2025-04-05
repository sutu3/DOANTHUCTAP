package com.ddd.scheduleservice.Service;

import com.ddd.scheduleservice.Client.FileService;
import com.ddd.scheduleservice.Dto.Request.UserRequest;
import com.ddd.scheduleservice.Dto.Response.ImageResponse;
import com.ddd.scheduleservice.Dto.Response.UserResponse;
import com.ddd.scheduleservice.Entity.User;
import com.ddd.scheduleservice.Exception.AppException;
import com.ddd.scheduleservice.Exception.ErrorCode;
import com.ddd.scheduleservice.Form.UserUpdate;
import com.ddd.scheduleservice.Mapper.UserMapper;
import com.ddd.scheduleservice.Repo.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
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

    
    public UserResponse getUserByUsername(String username) {
        return userMapper.toUserResponse(
                userRepo.findByUsername(username));
    }

  
    public UserResponse createUser(UserRequest request,MultipartFile file) {
        ImageResponse image= fileService.uploadFileImage(file);
        User user=userMapper.toUser(request);
        System.out.println(image.getUrlImage());
        user.setImageUrl(image.getUrlImage());
        return userMapper.toUserResponse(
                userRepo.save(user));
    }

   
    public UserResponse updateUser(int id, UserUpdate userDetails) {
        User user=userRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.GIANGVIEN_NOT_FOUND));
        userMapper.updateUser(user,userDetails);
        return userMapper.toUserResponse(
                userRepo.save(user));
    }

   
    public void deleteUser(int id) {
        userRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.GIANGVIEN_NOT_FOUND));
        userRepo.deleteById(id);
    }
}