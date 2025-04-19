package com.ddd.authenservice.Form;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User_Update {
    String email;
    String phonenumber;
    String fullname;
    String gender;
    String address;
    String city;
    String district;
}
