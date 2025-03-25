package com.example.serviceteacschedule_and_makeup_schedule_service.Controller;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.MonhocRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ApiResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.MonhocResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.MonhocUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Service.MonhocService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class MonhocController {
    MonhocService service;
    @GetMapping
    public ApiResponse<List<MonhocResponse>> getall(){
        return ApiResponse.<List<MonhocResponse>>builder()
                .Result(service.getall())
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<MonhocResponse> getById(@RequestParam String id){
        return ApiResponse.<MonhocResponse>builder()
                .Result(service.getbyId(id))
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
    @PostMapping
    public ApiResponse<MonhocResponse> create(@RequestBody MonhocRequest request){
        return ApiResponse.<MonhocResponse>builder()
                .Result(service.create(request))
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<MonhocResponse> update(@PathVariable String id, @RequestBody MonhocUpdate update){
        return ApiResponse.<MonhocResponse>builder()
                .Result(service.update(id,update))
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<String> Delete(@PathVariable String id){
        service.delete(id);
        return ApiResponse.<String>builder()
                .Result("Delete Success")
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
}
