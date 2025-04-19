package com.ddd.authenservice.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserRequest {
    String userName;
    String password;
    String email;
    String phoneNumber;
    List<String> roles;
}
