package com.ddd.authenservice.Mapper;

import com.ddd.authenservice.Dto.Request.PermissionRequest;
import com.ddd.authenservice.Dto.Response.PermissionResponse;
import com.ddd.authenservice.Entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
