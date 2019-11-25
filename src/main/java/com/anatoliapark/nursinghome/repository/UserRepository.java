package com.anatoliapark.nursinghome.repository;

import com.anatoliapark.nursinghome.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);


}
