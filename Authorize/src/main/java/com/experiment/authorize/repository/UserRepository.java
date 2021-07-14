package com.experiment.authorize.repository;

import com.experiment.authorize.entity.auth.UserEntity;
import com.experiment.authorize.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends EntityRepository<UserEntity> {

    Optional<UserEntity> findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);

}
