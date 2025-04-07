package com.ddd.scheduleservice.Controller;

import com.ddd.scheduleservice.Dto.Request.SubjectRequest;
import com.ddd.scheduleservice.Dto.Response.ApiResponse;
import com.ddd.scheduleservice.Dto.Response.SubjectResponse;
import com.ddd.scheduleservice.Form.SubjectUpdate;
import com.ddd.scheduleservice.Service.SubjectService;
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
public class SubjectController {
    SubjectService SubjectService;

    @GetMapping
    public ApiResponse<List<SubjectResponse>> getall(){
        return ApiResponse.<List<SubjectResponse>>builder()
                .Result(SubjectService.getAllSubjects())
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @PostMapping("/create")
    public ApiResponse<SubjectResponse> createSubject(@RequestBody SubjectRequest request,
                                                      @RequestHeader("Authorization") String authHeader){
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);  // Loại bỏ "Bearer " từ token
        }
        return ApiResponse.<SubjectResponse>builder()
                .Result(SubjectService.createSubject(request,token))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<SubjectResponse> getSubjectById(@PathVariable int id){
        return ApiResponse.<SubjectResponse>builder()
                .Result(SubjectService.getSubjectById(id))
                .success(true).code(0)
                .message("SuccessFull")
                .build();

    }
    @PutMapping("/update/{id}")
    public ApiResponse<SubjectResponse> updateSubject(@PathVariable int id,
                                                      @RequestBody SubjectUpdate request,
                                                      @RequestHeader("Authorization") String authHeader){
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);  // Loại bỏ "Bearer " từ token
        }
        return ApiResponse.<SubjectResponse>builder()
                .Result(SubjectService.updateSubject(id, request,token))
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteSubject(@PathVariable int id){
        SubjectService.deleteSubject(id);
        return ApiResponse.<String>builder()
                .Result("Deleted SuccessFull")
                .success(true).code(0)
                .message("SuccessFull")
                .build();
    }
}
