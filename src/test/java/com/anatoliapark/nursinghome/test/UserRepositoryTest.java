package com.anatoliapark.nursinghome.test;

import com.anatoliapark.nursinghome.NursingHomeApplication;
import com.anatoliapark.nursinghome.model.auth.AuthorityGroup;
import com.anatoliapark.nursinghome.model.auth.Role;
import com.anatoliapark.nursinghome.repository.constant.impl.ConstantRepositoryImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {NursingHomeApplication.class})
public class UserRepositoryTest {

    @Autowired
    ConstantRepositoryImpl constantRepository;

    @Test
    public void whenFindByName_returnConstantEntity(){
        AuthorityGroup authorityGroup = new AuthorityGroup();
        authorityGroup.setName("ADMIN");
        authorityGroup.setDsc("admin authority group");
        constantRepository.save(authorityGroup);

        assertThat(authorityGroup.getDsc()).isEqualTo( constantRepository.findByName("ADMIN",AuthorityGroup.class).getDsc());

        Role role = new Role();
        role.setName("ADMIN");
        role.setDsc("admin role");
        constantRepository.save(role);

        assertThat(role.getDsc()).isEqualTo(  constantRepository.findByName("ADMIN",Role.class).getDsc());
    }
}
