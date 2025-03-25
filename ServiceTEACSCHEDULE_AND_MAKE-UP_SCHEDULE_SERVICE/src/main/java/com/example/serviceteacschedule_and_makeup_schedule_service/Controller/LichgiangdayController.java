package com.example.serviceteacschedule_and_makeup_schedule_service.Controller;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.LichgiangdayRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ApiResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.LichgiangdayResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.LichgiangdayUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Service.LichgiangdayService;
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
public class LichgiangdayController {
    LichgiangdayService service;
    @GetMapping
    public ApiResponse<List<LichgiangdayResponse>> getall(){
        return ApiResponse.<List<LichgiangdayResponse>>builder()
                .Result(service.getall())
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<LichgiangdayResponse> getById(@RequestParam String id){
        return ApiResponse.<LichgiangdayResponse>builder()
                .Result(service.getbyId(id))
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
    @PostMapping
    public ApiResponse<LichgiangdayResponse> create(@RequestBody LichgiangdayRequest request){
        return ApiResponse.<LichgiangdayResponse>builder()
                .Result(service.create(request))
                .code(0)
                .success(true)
                .message("Success")
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<LichgiangdayResponse> update(@PathVariable String id, @RequestBody LichgiangdayUpdate update){
        return ApiResponse.<LichgiangdayResponse>builder()
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
