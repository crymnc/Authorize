package com.anatoliapark.nursinghome.test;

import com.anatoliapark.nursinghome.model.Privilege;
import com.anatoliapark.nursinghome.repository.PrivilegeRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class PrivilegeRepositoryTest {

    @Autowired
    PrivilegeRepository privilegeRepository;


    public List<Privilege> createPrivileges(){

        List<Privilege> privileges = new ArrayList<>();
        Privilege privilegeRead=new Privilege();
        privilegeRead.setName("READ");
        privilegeRead.setDescription("Read Desc");
        privileges.add(privilegeRead);

        Privilege privilegeWrite = new Privilege();
        privilegeWrite.setName("WRITE");
        privilegeWrite.setDescription("Write Desc");
        privileges.add(privilegeWrite);

        return privileges;
    }

    @Before
    public void init(){
        List<Privilege> privileges = createPrivileges();
        privilegeRepository.save(privileges);
    }

    @Test
    public void findByName(){

        Privilege privilegeRead = new Privilege();
        privilegeRead.setId(1L);
        privilegeRead.setName("READ");
        privilegeRead.setDescription("Read Desc");

        assertThat(privilegeRead,equalTo(privilegeRepository.findByName("READ")));

    }

}
