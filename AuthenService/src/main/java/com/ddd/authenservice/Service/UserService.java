package com.ddd.authenservice.Service;

import com.ddd.authenservice.Dto.Request.UserRequest;
import com.ddd.authenservice.Dto.Response.UserResponse;
import com.ddd.authenservice.Entity.Role;
import com.ddd.authenservice.Entity.User;
import com.ddd.authenservice.Enum.RoleEnum;
import com.ddd.authenservice.Exception.AppException;
import com.ddd.authenservice.Exception.ErrorCode;
import com.ddd.authenservice.Form.User_Update;
import com.ddd.authenservice.Mapper.UserMapper;
import com.ddd.authenservice.Repo.RoleRepository;
import com.ddd.authenservice.Repo.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {

    UserRepository userRepository;
    UserMapper mapper;
    RoleRepository roleRepository;

    PasswordEncoder passwordEncoder;

/*
    @PreAuthorize("hasRole('ADMIN')")
*/
    public List<UserResponse> getall() {
        var authentication=SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return userRepository.findAll().stream()
                .map(mapper::toUserResponse).collect(Collectors.toList());
    }

    @PostAuthorize("returnObject.userName==authentication.name")
    public UserResponse getmyInfor() {
        var context= SecurityContextHolder.getContext();
        String userName=context.getAuthentication().getName();
        return mapper.toUserResponse(userRepository.findByUserName(userName)
                .orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public UserResponse getbyId(String id) {
        return mapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse PostUser(UserRequest request) {


        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.USERNAME_IS_EXITED);
        }
        User user = mapper.toUser(request);
        Role role=roleRepository.findByName(RoleEnum.TEACHER.name());
        HashSet<Role> rolelist=new HashSet<Role>();
        rolelist.add(role);
        return mapper.toUserResponse(userRepository
                .save(user.builder()
                        .userName(request.getUserName())
                        .email(request.getEmail())
                        .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .createAt(LocalDateTime.now())
                .isDeleted(false)
                .roles(rolelist)
                .build()));
    }

    public UserResponse putUser(String id, User_Update update) {
        User userupdate = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        mapper.updateUser(userupdate, update);
        userupdate.setUpdateAt(LocalDateTime.now());
        return mapper.toUserResponse(userRepository
                .save(userupdate));
    }
    public UserResponse putUser(String id, UserRequest request) {
        User userupdate = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userupdate.setUpdateAt(LocalDateTime.now());
        return mapper.toUserResponse(userRepository
                .save(userupdate));
    }
    public void deleteUser(String id){
        User user=userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userRepository
                .save(user.builder()
                        .deleteAt(LocalDateTime.now())
                        .isDeleted(true)
                        .build());
    }
}
