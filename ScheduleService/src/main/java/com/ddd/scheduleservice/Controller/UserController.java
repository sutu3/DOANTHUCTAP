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
    private final UserService userService;

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
                                                @RequestPart("request") UserRequest request,@RequestHeader("Authorization") String authHeader) {
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);  // Loại bỏ "Bearer " từ token
        }
        return ApiResponse.<UserResponse>builder()
                .Result(UserService.createUser(request, file,token))
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
    @PutMapping("/updateRole/{id}")
    public ApiResponse<String> updateUser(@PathVariable int id){
        userService.UpdateRoleAdmin(id);
        return ApiResponse.<String>builder()
                .Result("User has Role Admin")
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @GetMapping("/myinfo")
    public ApiResponse<UserResponse> GetmyInfor(
            @RequestHeader("Authorization") String authHeader){
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);  // Loại bỏ "Bearer " từ token
        }

        return ApiResponse.<UserResponse>builder()
                .Result(UserService.getUserByToken(token))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteUser(
            @PathVariable int id,
            @RequestHeader("Authorization") String authHeader){
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);  // Loại bỏ "Bearer " từ token
        }
        UserService.deleteUser(id,token);
        return ApiResponse.<String>builder()
                .Result("Deleted SuccessFull")
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
}
