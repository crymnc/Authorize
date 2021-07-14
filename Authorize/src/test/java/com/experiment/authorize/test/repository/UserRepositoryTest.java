package com.experiment.authorize.test.repository;

import com.experiment.authorize.entity.auth.UserEntity;
import com.experiment.authorize.entity.base.BaseEntity;
import com.experiment.authorize.repository.UserRepository;
import com.experiment.authorize.repository.base.EntityRepository;
import com.experiment.authorize.test.utility.factory.UserEntityMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityRepository<BaseEntity> entityRepository;

    @Test
    public void shouldSaveUser(){
        UserEntity userEntity = UserEntityMother.builder().complete(entityRepository).build();
        UserEntity savedUsedEntity = userRepository.save(userEntity);
        assertNotNull(savedUsedEntity);
        assertNotNull(savedUsedEntity.getId());
        assertEquals(userRepository.findById(savedUsedEntity.getId()).get().getId(), savedUsedEntity.getId());
    }

    @Test
    public void shouldFindUserWithGivenUsername(){
        UserEntity userEntity = UserEntityMother.builder().complete(entityRepository).build();
        UserEntity savedUsedEntity = userRepository.save(userEntity);
        assertEquals(userRepository.findByUsername("username").get().getId(), savedUsedEntity.getId());
    }

    @Test
    public void shouldDeleteUserWithGivenUsername(){
        UserEntity userEntity = UserEntityMother.builder().complete(entityRepository).build();
        UserEntity savedUsedEntity = userRepository.save(userEntity);
        assertEquals(userRepository.findByUsername("username").get().getId(), savedUsedEntity.getId());
        userRepository.deleteByUsername("username");
        assertTrue(!userRepository.findByUsername("username").isPresent());
    }
}
