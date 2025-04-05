package com.ddd.scheduleservice.Controller;

import com.ddd.scheduleservice.Dto.Request.ClassSchedulesRequest;
import com.ddd.scheduleservice.Dto.Response.ApiResponse;
import com.ddd.scheduleservice.Dto.Response.ClassSchedulesResponse;
import com.ddd.scheduleservice.Service.ClassSchedulesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classSchedules")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ClassSchedulesController {
    ClassSchedulesService ClassSchedulesService;

    @GetMapping
    public ApiResponse<List<ClassSchedulesResponse>> getall(){
        return ApiResponse.<List<ClassSchedulesResponse>>builder()
                .Result(ClassSchedulesService.getAllClassSchedules())
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @PostMapping("/create")
    public ApiResponse<ClassSchedulesResponse> createClassSchedules(@RequestBody ClassSchedulesRequest request){
        return ApiResponse.<ClassSchedulesResponse>builder()
                .Result(ClassSchedulesService.createClassSchedule(request))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<ClassSchedulesResponse> getClassSchedulesById(@PathVariable int id){
        return ApiResponse.<ClassSchedulesResponse>builder()
                .Result(ClassSchedulesService.getClassScheduleById(id))
                .success(true).code(0)
                .message("SuccessFull")
                .build();

    }
    /*@PutMapping("/update/{id}")
    public ApiResponse<ClassSchedulesResponse> updateClassSchedules(@PathVariable int id, @RequestBody ClassSchedulesUpdate request){
        return ApiResponse.<ClassSchedulesResponse>builder()
                .Result(ClassSchedulesService(id, request))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }*/
    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteClassSchedules(@PathVariable int id){
        ClassSchedulesService.deleteClassSchedule(id);
        return ApiResponse.<String>builder()
                .Result("Deleted SuccessFull")
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
}
