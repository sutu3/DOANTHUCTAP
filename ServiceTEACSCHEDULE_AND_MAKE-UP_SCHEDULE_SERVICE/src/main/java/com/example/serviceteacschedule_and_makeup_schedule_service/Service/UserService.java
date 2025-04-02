package com.example.serviceteacschedule_and_makeup_schedule_service.Service;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.UserResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.User;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.AppException;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.ErrorCode;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.UserUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Mapper.UserMapper;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class UserService {
    UserMapper userMapper;

    UserRepo userRepo;


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

  
    public UserResponse createUser(User user) {

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