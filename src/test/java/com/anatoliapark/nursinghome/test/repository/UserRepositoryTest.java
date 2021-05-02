package com.anatoliapark.nursinghome.test.repository;

import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.entity.base.BaseEntity;
import com.anatoliapark.nursinghome.repository.UserRepository;
import com.anatoliapark.nursinghome.repository.base.EntityRepository;
import com.anatoliapark.nursinghome.test.utility.factory.UserEntityMother;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
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
        Assert.assertNotNull(savedUsedEntity);
        Assert.assertNotNull(savedUsedEntity.getId());
        Assert.assertEquals(userRepository.findById(savedUsedEntity.getId()).get().getId(), savedUsedEntity.getId());
    }

    @Test
    public void shouldFindUserWithGivenUsername(){
        UserEntity userEntity = UserEntityMother.builder().complete(entityRepository).build();
        UserEntity savedUsedEntity = userRepository.save(userEntity);
        Assert.assertEquals(userRepository.findByUsername("username").get().getId(), savedUsedEntity.getId());
    }

    @Test
    public void shouldDeleteUserWithGivenUsername(){
        UserEntity userEntity = UserEntityMother.builder().complete(entityRepository).build();
        UserEntity savedUsedEntity = userRepository.save(userEntity);
        Assert.assertEquals(userRepository.findByUsername("username").get().getId(), savedUsedEntity.getId());
        userRepository.deleteByUsername("username");
        Assert.assertTrue(!userRepository.findByUsername("username").isPresent());
    }
}
