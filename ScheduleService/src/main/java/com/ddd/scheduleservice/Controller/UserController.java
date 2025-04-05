package com.ddd.scheduleservice.Controller;

import com.ddd.scheduleservice.Dto.Request.UserRequest;
import com.ddd.scheduleservice.Dto.Response.ApiResponse;
import com.ddd.scheduleservice.Dto.Response.UserResponse;
import com.ddd.scheduleservice.Form.UserUpdate;
import com.ddd.scheduleservice.Service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class UserController {
    UserService UserService;

    @GetMapping
    public ApiResponse<List<UserResponse>> getall(){
        return ApiResponse.<List<UserResponse>>builder()
                .Result(UserService.getAllUsers())
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @PostMapping("/create")
    public ApiResponse<UserResponse> createUser(@RequestParam("file") MultipartFile file,
                                                @RequestPart("request") UserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .Result(UserService.createUser(request, file))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable int id){
        return ApiResponse.<UserResponse>builder()
                .Result(UserService.getUserById(id))
                .success(true).code(0)
                .message("SuccessFull")
                .build();

    }
    @PutMapping("/update/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable int id, @RequestBody UserUpdate request){
        return ApiResponse.<UserResponse>builder()
                .Result(UserService.updateUser(id, request))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteUser(@PathVariable int id){
        UserService.deleteUser(id);
        return ApiResponse.<String>builder()
                .Result("Deleted SuccessFull")
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
}
