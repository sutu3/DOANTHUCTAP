package com.ddd.scheduleservice.Controller;

import com.ddd.scheduleservice.Dto.Request.MakeupRequestDTORequest;
import com.ddd.scheduleservice.Dto.Response.ApiResponse;
import com.ddd.scheduleservice.Dto.Response.ClassSchedulesResponse;
import com.ddd.scheduleservice.Dto.Response.MakeupRequestDTOResponse;
import com.ddd.scheduleservice.Form.MakeupRequestUpdate;
import com.ddd.scheduleservice.Service.MakeupRequestService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/makeups")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class MakeupRequestController {
    MakeupRequestService MakeupRequestDTOService;
    private final MakeupRequestService makeupRequestService;

    @GetMapping
    public ApiResponse<List<MakeupRequestDTOResponse>> getall(){
        return ApiResponse.<List<MakeupRequestDTOResponse>>builder()
                .Result(MakeupRequestDTOService.getAllMakeupRequests())
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @PostMapping("/create")
    public ApiResponse<MakeupRequestDTOResponse> createMakeupRequestDTO(@RequestBody MakeupRequestDTORequest request){
        return ApiResponse.<MakeupRequestDTOResponse>builder()
                .Result(MakeupRequestDTOService.createMakeupRequest(request))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<MakeupRequestDTOResponse> getMakeupRequestDTOById(@PathVariable int id){
        return ApiResponse.<MakeupRequestDTOResponse>builder()
                .Result(MakeupRequestDTOService.getMakeupRequestById(id))
                .success(true).code(0)
                .message("SuccessFull")
                .build();

    }
    @PutMapping("/update/reject/{id}")
    public ApiResponse<MakeupRequestDTOResponse> updateRejectMakeupRequestDTO(@PathVariable int id,
                                                                              @RequestHeader("Authorization") String authHeader){
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);  // Loại bỏ "Bearer " từ token
        }
        return ApiResponse.<MakeupRequestDTOResponse>builder()
                .Result(makeupRequestService.RejectRequest(id,token))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @PutMapping("/update/approve/{id}")
    public ApiResponse<ClassSchedulesResponse> updateApproveMakeupRequestDTO(@PathVariable int id,
                                                                             @RequestBody MakeupRequestUpdate update,
                                                                             @RequestHeader("Authorization") String authHeader){
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);  // Loại bỏ "Bearer " từ token
        }
        return ApiResponse.<ClassSchedulesResponse>builder()
                .Result(makeupRequestService.Approve(id, update,token))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteMakeupRequestDTO(@PathVariable int id){
        MakeupRequestDTOService.deleteMakeupRequest(id);
        return ApiResponse.<String>builder()
                .Result("Deleted SuccessFull")
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
}
