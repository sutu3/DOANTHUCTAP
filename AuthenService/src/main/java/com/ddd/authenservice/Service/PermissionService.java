package com.ddd.authenservice.Service;

import com.ddd.authenservice.Dto.Request.PermissionRequest;
import com.ddd.authenservice.Dto.Response.PermissionResponse;
import com.ddd.authenservice.Entity.Permission;
import com.ddd.authenservice.Exception.AppException;
import com.ddd.authenservice.Exception.ErrorCode;
import com.ddd.authenservice.Mapper.PermissionMapper;
import com.ddd.authenservice.Repo.PermissonRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class PermissionService {

    PermissonRepository permissonRepository;
    PermissionMapper mapper;
    public List<PermissionResponse> getall() {
        return permissonRepository.findAll().stream()
                .map(mapper::toPermissionResponse).collect(Collectors.toList());
    }
    public PermissionResponse PostPermission(PermissionRequest request){
        Permission permission=mapper.toPermission(request);
        if(permissonRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.PERMISSION_IS_EXITED);
        }
        return mapper.toPermissionResponse(permissonRepository.save(permission));
    }

    public void deletePermission(String name) {
        permissonRepository.findById(name)
                .orElseThrow(()->new AppException(ErrorCode.PERMISSION_NOT_FOUND));
        permissonRepository.deleteById(name);
    }
}
