package com.anatoliapark.nursinghome.test;

import com.anatoliapark.nursinghome.domain.User;
import com.anatoliapark.nursinghome.entity.UserComponentContentEntity;
import com.anatoliapark.nursinghome.entity.UserComponentEntity;
import com.anatoliapark.nursinghome.entity.auth.AuthorityGroupEntity;
import com.anatoliapark.nursinghome.entity.auth.RoleEntity;
import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.mapper.UserMapper;
import com.anatoliapark.nursinghome.repository.base.EntityRepository;
import com.anatoliapark.nursinghome.service.base.EntityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
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


    @Before
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

        Mockito.when(entityService.find(1L,RoleEntity.class)).thenReturn(roleEntity);
        Mockito.when( entityService.find(1L,UserComponentEntity.class)).thenReturn(userComponentEntity);
    }

    @Test
    public void createUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Test Name");
        userEntity.setLastName("TLastName");
        userEntity.setUsername("TUsername");
        userEntity.setPassword("TPass");
        RoleEntity roleEntity = (RoleEntity) entityService.find(1L, RoleEntity.class);
        userEntity.addRole(roleEntity);
        UserComponentEntity userComponentEntity = (UserComponentEntity) entityService.find(1L,UserComponentEntity.class);
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

