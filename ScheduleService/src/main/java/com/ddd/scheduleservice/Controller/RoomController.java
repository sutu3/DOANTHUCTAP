package com.ddd.scheduleservice.Controller;

import com.ddd.scheduleservice.Dto.Request.RoomRequest;
import com.ddd.scheduleservice.Dto.Response.ApiResponse;
import com.ddd.scheduleservice.Dto.Response.RoomResponse;
import com.ddd.scheduleservice.Form.RoomUpdate;
import com.ddd.scheduleservice.Service.RoomService;
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
public class RoomController {
    RoomService RoomService;

    @GetMapping
    public ApiResponse<List<RoomResponse>> getall(){
        return ApiResponse.<List<RoomResponse>>builder()
                .Result(RoomService.getAllRooms())
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @PostMapping("/create")
    public ApiResponse<RoomResponse> createRoom(@RequestBody RoomRequest request){
        return ApiResponse.<RoomResponse>builder()
                .Result(RoomService.createRoom(request))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<RoomResponse> getRoomById(@PathVariable int id){
        return ApiResponse.<RoomResponse>builder()
                .Result(RoomService.getRoomById(id))
                .success(true).code(0)
                .message("SuccessFull")
                .build();

    }
    @PutMapping("/update/{id}")
    public ApiResponse<RoomResponse> updateRoom(@PathVariable int id, @RequestBody RoomUpdate request){
        return ApiResponse.<RoomResponse>builder()
                .Result(RoomService.updateRoom(id, request))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteRoom(@PathVariable int id){
        RoomService.deleteRoom(id);
        return ApiResponse.<String>builder()
                .Result("Deleted SuccessFull")
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
}
