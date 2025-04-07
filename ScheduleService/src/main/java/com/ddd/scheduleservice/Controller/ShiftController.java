package com.ddd.scheduleservice.Controller;

import com.ddd.scheduleservice.Dto.Request.ShiftRequest;
import com.ddd.scheduleservice.Dto.Response.ApiResponse;
import com.ddd.scheduleservice.Dto.Response.ShiftResponse;
import com.ddd.scheduleservice.Form.ShiftUpdate;
import com.ddd.scheduleservice.Service.ShiftService;
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
    public ApiResponse<ShiftResponse> createShift(@RequestBody ShiftRequest request,
                                                  @RequestHeader("Authorization") String authHeader){
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);  // Loại bỏ "Bearer " từ token
        }
        return ApiResponse.<ShiftResponse>builder()
                .Result(shiftService.createShift(request,token))
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
    public ApiResponse<ShiftResponse> updateShift(@PathVariable int id,
                                                  @RequestBody ShiftUpdate request,
                                                  @RequestHeader("Authorization") String authHeader){
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);  // Loại bỏ "Bearer " từ token
        }
        return ApiResponse.<ShiftResponse>builder()
                .Result(shiftService.updateShift(id, request,token))
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
