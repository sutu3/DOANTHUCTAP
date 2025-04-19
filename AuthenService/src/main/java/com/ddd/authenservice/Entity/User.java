package com.ddd.authenservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    String idUser;
    String userName;
    String password;
    String email;
    @ManyToMany
    Set<Role> roles;
    String phoneNumber;
    LocalDateTime createAt;
    LocalDateTime updateAt;
    LocalDateTime deleteAt;
    boolean isDeleted;

}
