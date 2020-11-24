package com.anatoliapark.nursinghome.repository;

import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.repository.base.EntityRepository;

public interface UserRepository extends EntityRepository<UserEntity> {

    UserEntity findByUsername(String username);
}
