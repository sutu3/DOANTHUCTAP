package com.ddd.authenservice.Mapper;

import com.ddd.authenservice.Dto.Request.RoleRequest;
import com.ddd.authenservice.Dto.Response.RoleResponse;
import com.ddd.authenservice.Entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions",ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
