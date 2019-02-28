package com.anatoliapark.nursinghome;

import com.anatoliapark.nursinghome.model.base.User;
import com.anatoliapark.nursinghome.repository.UserRepository;
import com.anatoliapark.nursinghome.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NursingHomeApplicationTest {

    @Autowired
    UserRepository userRepository;


    @Test
    public void contextLoads() {
        userRepository.save(new User());
    }
}
