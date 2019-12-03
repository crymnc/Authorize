package com.anatoliapark.nursinghome.test;

import com.anatoliapark.nursinghome.NursingHomeApplication;
import com.anatoliapark.nursinghome.model.auth.*;
import com.anatoliapark.nursinghome.model.webpage.WebPage;
import com.anatoliapark.nursinghome.model.webpage.WebPageComponent;
import com.anatoliapark.nursinghome.model.webpage.WebPageComponentType;
import com.anatoliapark.nursinghome.repository.ConstantRepository;
import com.anatoliapark.nursinghome.repository.EntityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {NursingHomeApplication.class})
@TestPropertySource(locations="classpath:application.properties")
public class AuthenticationServiceTest {

    @Autowired
    ConstantRepository constantRepository;

    @Autowired
    EntityRepository entityRepository;


    @Before
    public void createBaseSamples(){
        WebPageComponentType webPageComponentType = new WebPageComponentType();
        webPageComponentType.setName("Button");
        webPageComponentType.setDsc("Button");
        constantRepository.save(webPageComponentType);

        WebPageComponent webPageComponentCreateButton = new WebPageComponent();
        webPageComponentCreateButton.setComponentType(constantRepository.findByName("Button",WebPageComponentType.class));
        webPageComponentCreateButton.setName("CreateButton");
        webPageComponentCreateButton.setDsc("Create Button");
        constantRepository.save(webPageComponentCreateButton);

        WebPageComponent webPageComponentDeleteButton = new WebPageComponent();
        webPageComponentDeleteButton.setComponentType(constantRepository.findByName("Button",WebPageComponentType.class));
        webPageComponentDeleteButton.setName("DeleteButton");
        webPageComponentDeleteButton.setDsc("Delete Button");
        constantRepository.save(webPageComponentDeleteButton);

        WebPage webPage = new WebPage();
        webPage.setName("UserManagement");
        webPage.setDsc("Manage your users");
        webPage.setWebPageComponents(constantRepository.findAll(WebPageComponent.class));
        constantRepository.save(webPage);

        Authority authority = new Authority();
        authority.setName("CreateUser");
        authority.setDsc("Create User");
        constantRepository.save(authority);

        AuthorityOption authorityOption = new AuthorityOption();
        authorityOption.setActive(true);
        authorityOption.setVisible(true);
        authorityOption.setAuthority(constantRepository.findByName("CreateUser",Authority.class));
        authorityOption.setWebPage(constantRepository.findByName("UserManagement",WebPage.class));
        authorityOption.setComponent(constantRepository.findByName("CreateButton",WebPageComponent.class));
        entityRepository.save(authorityOption);

        AuthorityGroup authorityGroup = new AuthorityGroup();
        authorityGroup.setAuthorities(constantRepository.findAll(Authority.class));
        authorityGroup.setName("AdminGroup");
        authorityGroup.setDsc("Admin Authority Group");
        constantRepository.save(authorityGroup);

        Role role = new Role();
        role.setName("ADMIN");
        role.setDsc("Admin Role");
        role.setAuthorityGroups(constantRepository.findAll(AuthorityGroup.class));
        constantRepository.save(role);

    }


    @Test
    public void createNewUser(){
        User user = new User();
        user.setUsername("crymnc");
        user.setPassword("123456");
        user.setName("Can");
        user.setLastName("Şahintaş");
        user.setRoles(Arrays.asList(constantRepository.findByName("ADMIN",Role.class)));
        User savedUser = entityRepository.save(user);

        assertThat(user.getUsername()).isEqualTo( savedUser.getUsername());
    }

    @Test
    public void updateUser(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("username","crymnc");
        List<User> userList = entityRepository.findBy(parameters,User.class);
        User user = userList.get(0);
        assertThat(user.getUsername()).isEqualTo("crymnc");

        user.setPassword("234567");
        entityRepository.save(user);

        userList = entityRepository.findBy(parameters,User.class);
        user = userList.get(0);
        assertThat(user.getPassword()).isEqualTo("234567");
    }
}
