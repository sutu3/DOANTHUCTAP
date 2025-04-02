package com.example.serviceteacschedule_and_makeup_schedule_service.Controller;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.ShiftRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ApiResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ShiftResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.ShiftUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Service.ShiftService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shifts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ShiftController {
    ShiftService shiftService;

    @GetMapping
    public ApiResponse<List<ShiftResponse>> getall(){
        return ApiResponse.<List<ShiftResponse>>builder()
                .Result(shiftService.getAllShifts())
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @PostMapping("/create")
    public ApiResponse<ShiftResponse> createShift(@RequestBody ShiftRequest request){
        return ApiResponse.<ShiftResponse>builder()
                .Result(shiftService.createShift(request))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<ShiftResponse> getShiftById(@PathVariable int id){
        return ApiResponse.<ShiftResponse>builder()
                .Result(shiftService.getShiftById(id))
                .success(true).code(0)
                .message("SuccessFull")
                .build();

    }
    @PutMapping("/update/{id}")
    public ApiResponse<ShiftResponse> updateShift(@PathVariable int id, @RequestBody ShiftUpdate request){
        return ApiResponse.<ShiftResponse>builder()
                .Result(shiftService.updateShift(id, request))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteShift(@PathVariable int id){
        shiftService.deleteShift(id);
        return ApiResponse.<String>builder()
                .Result("Deleted SuccessFull")
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
}
