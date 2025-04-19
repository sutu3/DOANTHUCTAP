package com.ddd.scheduleservice.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserRequestAuthenService {
    String userName;
    String password;
    String email;
    String phoneNumber;
    List<String> roles;
}
