package com.ddd.authenservice.Service;

import com.ddd.authenservice.Dto.Request.RoleRequest;
import com.ddd.authenservice.Dto.Response.RoleResponse;
import com.ddd.authenservice.Entity.Role;
import com.ddd.authenservice.Exception.AppException;
import com.ddd.authenservice.Exception.ErrorCode;
import com.ddd.authenservice.Mapper.RoleMapper;
import com.ddd.authenservice.Repo.PermissonRepository;
import com.ddd.authenservice.Repo.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class RoleService {

    RoleRepository roleRepository;
    PermissonRepository permissionRepository;
    RoleMapper mapper;
    public List<RoleResponse> getall() {
        return roleRepository.findAll().stream()
                .map(mapper::toRoleResponse).collect(Collectors.toList());
    }
    public RoleResponse PostRole(RoleRequest request){
        Role role=mapper.toRole(request);
        var permission=permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permission));
        if(roleRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.ROLE_IS_EXITED);
        }
        return mapper.toRoleResponse(roleRepository.save(role));
    }

    public void deleteRole(String name) {
        roleRepository.findById(name)
                .orElseThrow(()->new AppException(ErrorCode.ROLE_NOT_FOUND));
        roleRepository.deleteById(name);
    }
}
