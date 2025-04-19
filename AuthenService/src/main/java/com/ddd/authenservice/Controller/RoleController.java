package com.ddd.authenservice.Controller;


import com.ddd.authenservice.Dto.Request.RoleRequest;
import com.ddd.authenservice.Dto.Response.ApiResponse;
import com.ddd.authenservice.Dto.Response.RoleResponse;
import com.ddd.authenservice.Service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;
    @GetMapping
    public ApiResponse<List<RoleResponse>> getall(){
        return ApiResponse.<List<RoleResponse>>builder()
                .Result(roleService.getall())
                .code(0)
                .success(true)
                .message("Completed")
                .build();
    }
    @PostMapping
    public ApiResponse<RoleResponse> postrole(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .Result(roleService.PostRole(request))
                .code(0)
                .message("Completed")
                .success(true)
                .build();
    }
    @DeleteMapping("/{role}")
    public ApiResponse<Void> deleteSize(@PathVariable String role)  {
        roleService.deleteRole(role);
        return ApiResponse.<Void>builder()
                .message("Delete Completed")
                .code(0)
                .success(true)
                .build();
    }
}
