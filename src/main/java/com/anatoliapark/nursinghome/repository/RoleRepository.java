package com.anatoliapark.nursinghome.repository;

import com.anatoliapark.nursinghome.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
