package com.example.serviceteacschedule_and_makeup_schedule_service.Controller;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.GiangvienRequet;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.LopRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ApiResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.LopResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.LopResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.GiangvienUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.LopUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Service.GiangvienService;
import com.example.serviceteacschedule_and_makeup_schedule_service.Service.LopService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class LopController {
    LopService service;
    @GetMapping
    public ApiResponse<List<LopResponse>> getall(){
        return ApiResponse.<List<LopResponse>>builder()
                .Result(service.getall())
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<LopResponse> getById(@RequestParam String id){
        return ApiResponse.<LopResponse>builder()
                .Result(service.getbyId(id))
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
    @PostMapping
    public ApiResponse<LopResponse> create(@RequestBody LopRequest request){
        return ApiResponse.<LopResponse>builder()
                .Result(service.create(request))
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<LopResponse> update(@PathVariable String id, @RequestBody LopUpdate update){
        return ApiResponse.<LopResponse>builder()
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
