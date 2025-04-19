package com.ddd.authenservice.Repo;

import com.ddd.authenservice.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissonRepository extends JpaRepository<Permission,String> {
    boolean existsByName(String name);
}
