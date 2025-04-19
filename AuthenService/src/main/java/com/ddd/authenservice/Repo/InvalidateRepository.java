package com.ddd.authenservice.Repo;

import com.ddd.authenservice.Entity.InvalidateToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidateRepository extends JpaRepository<InvalidateToken,String> {
}
