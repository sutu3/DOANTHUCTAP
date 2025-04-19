package com.ddd.authenservice.Configuration;


import com.ddd.authenservice.Entity.Role;
import com.ddd.authenservice.Entity.User;
import com.ddd.authenservice.Enum.RoleEnum;
import com.ddd.authenservice.Repo.RoleRepository;
import com.ddd.authenservice.Repo.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {


    PasswordEncoder passwordEncoder;
    @Bean

    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository){
        return args -> {
            // Initial data setup
            if(userRepository.findByUserName("admin").isEmpty()) {

                Role roleTeacher=roleRepository.save(Role.builder()
                        .name(RoleEnum.TEACHER.name())
                        .description("Teach role has all permission about: Watch Schedule Your Teacher ,Makeup Request Schedule")
                        .build());
                Role roleAdmin=roleRepository.save(Role.builder()
                                .name(RoleEnum.ADMIN.name())
                                .description("Admin role has all permission about: Create Teacher, Schedule Teacher,Approve Makeup Request Schedule and Reject it")
                        .build());
                HashSet<Role> roles=new HashSet<Role>();
                roles.add(roleAdmin);
                roles.add(roleTeacher);
                User user=User.builder()
                        .userName("admin")
                        .password(passwordEncoder.encode("admin"))
                        .createAt(LocalDateTime.now())
                        .roles(roles)
                        .isDeleted(false)
                        .build();
                userRepository.save(user);
            }

            log.warn("user admin created with default password username is admin");
            };
    }
}
