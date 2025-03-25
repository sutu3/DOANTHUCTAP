package com.example.serviceteacschedule_and_makeup_schedule_service.Controller;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.PhongmayRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ApiResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.PhongmayResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.PhongmayUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Service.PhongmayService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class PhongmayController {
    PhongmayService service;
    @GetMapping
    public ApiResponse<List<PhongmayResponse>> getall(){
        return ApiResponse.<List<PhongmayResponse>>builder()
                .Result(service.getall())
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<PhongmayResponse> getById(@RequestParam String id){
        return ApiResponse.<PhongmayResponse>builder()
                .Result(service.getbyId(id))
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
    @PostMapping
    public ApiResponse<PhongmayResponse> create(@RequestBody PhongmayRequest request){
        return ApiResponse.<PhongmayResponse>builder()
                .Result(service.create(request))
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<PhongmayResponse> update(@PathVariable String id, @RequestBody PhongmayUpdate update){
        return ApiResponse.<PhongmayResponse>builder()
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
