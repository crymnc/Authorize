package com.anatoliapark.nursinghome.test;

import com.anatoliapark.nursinghome.NursingHomeApplication;
import com.anatoliapark.nursinghome.model.auth.Role;
import com.anatoliapark.nursinghome.repository.base.ConstantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {NursingHomeApplication.class})
public class UserRepositoryTest {

    @Autowired
    ConstantRepository constantRepository;
    @Test
    public void registerUser(){
        constantRepository.findByName("ADMIN", Role.class);
    }
}
