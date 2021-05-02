package com.anatoliapark.nursinghome.test.service;

import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.repository.UserRepository;
import com.anatoliapark.nursinghome.repository.base.EntityRepository;
import com.anatoliapark.nursinghome.service.ConstantService;
import com.anatoliapark.nursinghome.service.UserService;
import com.anatoliapark.nursinghome.test.utility.factory.UserEntityMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EntityRepository entityRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @MockBean
    private ConstantService constantService;

    @BeforeEach
    public void setUp(){
        userService = new UserService();
    }

    @Test
    void saveUser() {
        UserEntity userEntity = UserEntityMother.builder().complete(entityRepository).build();
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        UserEntity createdUserEntity = userService.saveUser(userEntity);
        assertNotNull(createdUserEntity);
        assertEquals(userEntity.getUsername(),createdUserEntity.getUsername());
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUserById() {
    }

    @Test
    void deleteUserByUsername() {
    }

    @Test
    void findUserByUsername() {
    }

    @Test
    void findUserById() {
    }

    @Test
    void findLoggedUser() {
    }

    @Test
    @Disabled
    void canFindAllUsers() {
        given(entityRepository.findAll(any(Example.class))).willReturn(UserEntityMother.generateRandom(5));
        List<UserEntity> userEntities = userService.findAllUsers();
    }
}