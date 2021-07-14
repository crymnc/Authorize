package com.experiment.authorize.test;

import com.experiment.authorize.domain.User;
import com.experiment.authorize.entity.UserComponentContentEntity;
import com.experiment.authorize.entity.UserComponentEntity;
import com.experiment.authorize.entity.auth.AuthorityGroupEntity;
import com.experiment.authorize.entity.auth.RoleEntity;
import com.experiment.authorize.entity.auth.UserEntity;
import com.experiment.authorize.mapper.UserMapper;
import com.experiment.authorize.repository.base.EntityRepository;
import com.experiment.authorize.service.base.EntityService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class UserRepositoryIntegrationTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public EntityService entityService() {
            return new EntityService();
        }
    }
    @Autowired
    private EntityService entityService;

    @MockBean
    @Qualifier(value = "entityRepository")
    private EntityRepository entityRepository;

    @Autowired
    UserMapper userMapper;


    @Before(value="")
    public void setUp() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setName("TestRole");
        roleEntity.setDsc("TestDesc");
        UserComponentEntity userComponentEntity = new UserComponentEntity();
        userComponentEntity.setId(1L);
        userComponentEntity.setName("TestComponent");
        userComponentEntity.setDsc("TestComponent");
        userComponentEntity.addRole(roleEntity);
        roleEntity.addComponent(userComponentEntity);
        AuthorityGroupEntity authorityGroupEntity = new AuthorityGroupEntity();
        authorityGroupEntity.setId(1L);
        authorityGroupEntity.setName("TestAuthorityGroup");
        authorityGroupEntity.setDsc("TestAuthorityGroup");
        authorityGroupEntity.addRole(roleEntity);
        roleEntity.addAuthorityGroup(authorityGroupEntity);

        Mockito.when(entityService.find(1L,RoleEntity.class).get()).thenReturn(roleEntity);
        Mockito.when( entityService.find(1L,UserComponentEntity.class).get()).thenReturn(userComponentEntity);
    }

    @Test
    public void createUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Test Name");
        userEntity.setLastName("TLastName");
        userEntity.setUsername("TUsername");
        userEntity.setPassword("TPass");
        RoleEntity roleEntity = entityService.find(1L, RoleEntity.class).get();
        userEntity.addRole(roleEntity);
        UserComponentEntity userComponentEntity = entityService.find(1L,UserComponentEntity.class).get();
        UserComponentContentEntity userComponentContentEntity = new UserComponentContentEntity();
        userComponentContentEntity.setComponent(userComponentEntity);
        userComponentContentEntity.setUser(userEntity);
        userComponentContentEntity.setContent("TContent");
        userEntity.addUserComponentContent(userComponentContentEntity);
        User user = userMapper.toDomain(userEntity);

    }

    @Test
    public void updateUser(){

    }

    @Test
    public void removeUser(){

    }
}

