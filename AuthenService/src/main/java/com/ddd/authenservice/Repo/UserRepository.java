package com.ddd.authenservice.Repo;

import com.ddd.authenservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByUserName(String username);
    boolean existsByEmail(String email);

    Optional<User> findByUserName(String username);
}
