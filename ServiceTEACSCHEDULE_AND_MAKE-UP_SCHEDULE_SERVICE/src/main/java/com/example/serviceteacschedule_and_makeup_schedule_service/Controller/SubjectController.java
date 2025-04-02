package com.example.serviceteacschedule_and_makeup_schedule_service.Controller;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.SubjectRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.ApiResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.SubjectResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.SubjectUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Service.SubjectService;
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
    public ApiResponse<SubjectResponse> createSubject(@RequestBody SubjectRequest request){
        return ApiResponse.<SubjectResponse>builder()
                .Result(SubjectService.createSubject(request))
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
    public ApiResponse<SubjectResponse> updateSubject(@PathVariable int id, @RequestBody SubjectUpdate request){
        return ApiResponse.<SubjectResponse>builder()
                .Result(SubjectService.updateSubject(id, request))
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
