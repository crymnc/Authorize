package com.anatoliapark.nursinghome.repository;

import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends EntityRepository<UserEntity> {

    UserEntity findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);

}
