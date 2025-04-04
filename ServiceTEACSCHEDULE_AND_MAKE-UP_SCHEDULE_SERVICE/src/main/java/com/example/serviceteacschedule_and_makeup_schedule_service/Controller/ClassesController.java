package com.example.serviceteacschedule_and_makeup_schedule_service.Controller;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.ClassesRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ApiResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ClassesResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.ClassesUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Service.ClassesService;
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
    public ApiResponse<ClassesResponse> createClasses(@RequestBody ClassesRequest request){
        return ApiResponse.<ClassesResponse>builder()
                .Result(ClassesService.createClass(request))
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
