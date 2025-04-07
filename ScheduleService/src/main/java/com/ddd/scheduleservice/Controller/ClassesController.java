package com.ddd.scheduleservice.Controller;

import com.ddd.scheduleservice.Dto.Request.ClassesRequest;
import com.ddd.scheduleservice.Dto.Response.ApiResponse;
import com.ddd.scheduleservice.Dto.Response.ClassesResponse;
import com.ddd.scheduleservice.Form.ClassesUpdate;
import com.ddd.scheduleservice.Service.ClassesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classess")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ClassesController {
    ClassesService ClassesService;

    @GetMapping
    public ApiResponse<List<ClassesResponse>> getall(){
        return ApiResponse.<List<ClassesResponse>>builder()
                .Result(ClassesService.getAll())
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @PostMapping("/create")
    public ApiResponse<ClassesResponse> createClasses(@RequestBody ClassesRequest request,
                                                      @RequestHeader("Authorization") String authHeader){
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);  // Loại bỏ "Bearer " từ token
        }
        return ApiResponse.<ClassesResponse>builder()
                .Result(ClassesService.createClass(request,token))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<ClassesResponse> getClassesById(@PathVariable int id){
        return ApiResponse.<ClassesResponse>builder()
                .Result(ClassesService.getById(id))
                .success(true).code(0)
                .message("SuccessFull")
                .build();

    }
    @PutMapping("/update/{id}")
    public ApiResponse<ClassesResponse> updateClasses(@PathVariable int id, @RequestBody ClassesUpdate request){
        return ApiResponse.<ClassesResponse>builder()
                .Result(ClassesService.updateClass(id, request))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteClasses(@PathVariable int id){
        ClassesService.deleteClass(id);
        return ApiResponse.<String>builder()
                .Result("Deleted SuccessFull")
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
}
