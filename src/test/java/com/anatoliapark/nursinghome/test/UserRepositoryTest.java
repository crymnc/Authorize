package com.anatoliapark.nursinghome.test;

import com.anatoliapark.nursinghome.model.*;
import com.anatoliapark.nursinghome.model.usertypes.Attendant;
import com.anatoliapark.nursinghome.repository.PrivilegeRepository;
import com.anatoliapark.nursinghome.repository.RoleRepository;
import com.anatoliapark.nursinghome.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class UserRepositoryTest {


    @Autowired
    PrivilegeRepository privilegeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Before
    public void createPrivileges() {

        List<Privilege> privileges = new ArrayList<>();
        Privilege privilegeRead = new Privilege();
        privilegeRead.setName("READ");
        privilegeRead.setDescription("Read Desc");
        privileges.add(privilegeRead);

        Privilege privilegeWrite = new Privilege();
        privilegeWrite.setName("WRITE");
        privilegeWrite.setDescription("Write Desc");
        privileges.add(privilegeWrite);

        privilegeRepository.save(privileges);
        privilegeRepository.flush();

        Role roleDoctor = new Role();
        roleDoctor.setName("ROLE_DOCTOR");
        roleDoctor.setPrivileges(privileges);

        roleRepository.save(roleDoctor);
        roleRepository.flush();

    }

    @Test
    public void createAttendantTest() {
        Attendant attendant = new Attendant();
        attendant.setName("Can");
        attendant.setLastName("Şahintaş");
        attendant.setActive(true);
        attendant.setIdentifierNumber("44335061152");
        attendant.setUsername("crymnc");
        attendant.setPassword("a2m8z7mta");
        attendant.setRegistrationDate(new Date());
        attendant.setEducation("Doktor");

        Collection<Role> roles = new ArrayList<>();
        Role roleDoctor = roleRepository.findByName("ROLE_DOCTOR");
        roles.add(roleDoctor);
        attendant.setRoles(roles);

        Collection<UserAddress> addresses = new ArrayList<>();
        UserAddress address = new UserAddress();
        address.setAddress("İstanbul");
        addresses.add(address);
        attendant.setAddresses(addresses);

        Collection<UserPhone> phones = new ArrayList<>();
        UserPhone phone = new UserPhone();
        phone.setPhone("5319684944");
        phones.add(phone);
        attendant.setPhones(phones);

        Collection<UserEmail> emails = new ArrayList<>();
        UserEmail email = new UserEmail();
        email.setEmail("can.sahintas2324@gmail.com");
        emails.add(email);
        attendant.setEmails(emails);

        assertThat(attendant,equalTo(userService.registerNewUser(attendant)));
    }
}
