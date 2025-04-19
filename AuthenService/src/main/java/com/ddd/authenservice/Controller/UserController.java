package com.ddd.authenservice.Controller;


import com.ddd.authenservice.Dto.Request.UserRequest;
import com.ddd.authenservice.Dto.Response.ApiResponse;
import com.ddd.authenservice.Dto.Response.UserResponse;
import com.ddd.authenservice.Form.User_Update;
import com.ddd.authenservice.Service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@CrossOrigin(origins = {"http://localhost:5173", "http://26.144.191.229:5173","http://26.232.136.42:5173"})
@Slf4j
public class UserController {
    UserService userService;
    @GetMapping
    public ApiResponse<List<UserResponse>> getall(){
        return ApiResponse.<List<UserResponse>>builder()
                .Result(userService.getall())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }

    @GetMapping("/myinfor")
    public ApiResponse<UserResponse> getmyinfor(){
        return ApiResponse.<UserResponse>builder()
                .Result(userService.getmyInfor())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getbyId(@PathVariable String id) {
        return ApiResponse.<UserResponse>builder()
                .Result(userService.getbyId(id))
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping
    public ApiResponse<UserResponse> postUser(@RequestBody UserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .Result(userService.PostUser(request))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<UserResponse> putUser(@PathVariable String id, @RequestBody User_Update update)
            {
        return ApiResponse.<UserResponse>builder()
                .Result(userService.putUser(id, update))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @PutMapping("/upload/{id}")
    public ApiResponse<UserResponse> putUserImage(@PathVariable String id, @RequestBody UserRequest update)
    {
        return ApiResponse.<UserResponse>builder()
                .Result(userService.putUser(id, update))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return ApiResponse.<Void>builder()
                .message("Delete Completed")
                .code(0)
                .success(true)
                .build();
    }
}
