package com.example.serviceteacschedule_and_makeup_schedule_service.Controller;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.DangkybuRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ApiResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.DangkybuResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.DangkybuUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Service.DangkybuService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dangkybu")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class DangkybuController {
    DangkybuService service;
    @GetMapping
    public ApiResponse<List<DangkybuResponse>> getall(){
        return ApiResponse.<List<DangkybuResponse>>builder()
                .Result(service.getall())
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<DangkybuResponse> getById(@RequestParam String id){
        return ApiResponse.<DangkybuResponse>builder()
                .Result(service.getbyId(id))
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
    @PostMapping
    public ApiResponse<DangkybuResponse> create(@RequestBody DangkybuRequest request){
        return ApiResponse.<DangkybuResponse>builder()
                .Result(service.create(request))
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<DangkybuResponse> update(@PathVariable String id, @RequestBody DangkybuUpdate update){
        return ApiResponse.<DangkybuResponse>builder()
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
