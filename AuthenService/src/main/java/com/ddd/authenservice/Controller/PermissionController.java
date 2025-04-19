package com.ddd.authenservice.Controller;


import com.ddd.authenservice.Dto.Request.PermissionRequest;
import com.ddd.authenservice.Dto.Response.ApiResponse;
import com.ddd.authenservice.Dto.Response.PermissionResponse;
import com.ddd.authenservice.Service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class PermissionController {
    PermissionService permissionService;
    @GetMapping
    public ApiResponse<List<PermissionResponse>> getall(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .Result(permissionService.getall())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping
    public ApiResponse<PermissionResponse> postpermission(@RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .Result(permissionService.PostPermission(request))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @DeleteMapping("/{permission}")
    public ApiResponse<Void> deleteSize(@PathVariable String permission)  {
        permissionService.deletePermission(permission);
        return ApiResponse.<Void>builder()
                .message("Delete Completed")
                .code(0)
                .success(true)
                .build();
    }
}
