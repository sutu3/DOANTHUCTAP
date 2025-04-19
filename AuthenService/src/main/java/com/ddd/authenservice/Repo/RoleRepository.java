package com.ddd.authenservice.Repo;

import com.ddd.authenservice.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    boolean existsByName(String name);
    Role findByName(String name);
}
