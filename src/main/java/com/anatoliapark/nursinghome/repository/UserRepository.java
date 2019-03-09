package com.anatoliapark.nursinghome.repository;

import com.anatoliapark.nursinghome.model.base.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);


}
