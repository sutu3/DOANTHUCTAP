package com.example.serviceteacschedule_and_makeup_schedule_service.Controller;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.MakeupRequestDTORequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ApiResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ClassSchedulesResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.MakeupRequestDTOResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.MakeupRequestUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Service.MakeupRequestService;
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
    public ApiResponse<MakeupRequestDTOResponse> updateRejectMakeupRequestDTO(@PathVariable int id){
        return ApiResponse.<MakeupRequestDTOResponse>builder()
                .Result(makeupRequestService.RejectRequest(id))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @PutMapping("/update/approve/{id}")
    public ApiResponse<ClassSchedulesResponse> updateApproveMakeupRequestDTO(@PathVariable int id, @RequestBody MakeupRequestUpdate update){
        return ApiResponse.<ClassSchedulesResponse>builder()
                .Result(makeupRequestService.Approve(id, update))
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
