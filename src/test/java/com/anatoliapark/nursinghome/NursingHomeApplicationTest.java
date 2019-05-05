package com.anatoliapark.nursinghome;

import com.anatoliapark.nursinghome.model.*;
import com.anatoliapark.nursinghome.model.base.User;
import com.anatoliapark.nursinghome.model.usertypes.Attendant;
import com.anatoliapark.nursinghome.model.usertypes.Client;
import com.anatoliapark.nursinghome.model.usertypes.Guest;
import com.anatoliapark.nursinghome.repository.UserRepository;
import com.anatoliapark.nursinghome.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NursingHomeApplicationTest {

    @Autowired
    UserService userService;




    @Test
    public void createUserWithRoleAndPrivilege(){
        Client client = createClient();
        Guest guest = createGuest();
        createAffinityBetweenUsers(client,guest);
    }


    public Client createClient(){
        Client client=new Client();
        client.setName("Sevgi");
        client.setLastName("Şahintaş");
        client.setActive(true);
        client.setIdentifierNumber("11223344556");
        client.setUsername("sevgi");
        client.setPassword("a2m8z7mta");
        client.setRegistrationDate(new Date());
        client.setAffinity("Anne");


        Collection<Privilege> privileges = new ArrayList<>();
        Privilege privilege=new Privilege();
        privilege.setName("READ");
        privilege.setDescription("Read Privilige");
        privileges.add(privilege);

        Collection<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName("ROLE_USER");
        role.setPrivileges(privileges);
        roles.add(role);
        client.setRoles(roles);

        Collection<UserAddress> addresses = new ArrayList<>();
        UserAddress address=new UserAddress();
        address.setAddress("İstanbul");
        addresses.add(address);
        client.setAddresses(addresses);

        Collection<UserPhone> phones = new ArrayList<>();
        UserPhone phone = new UserPhone();
        phone.setPhone("5319684944");
        phones.add(phone);
        client.setPhones(phones);

        Collection<UserEmail> emails=new ArrayList<>();
        UserEmail email = new UserEmail();
        email.setEmail("sevgi.sahintas2324@gmail.com");
        emails.add(email);
        client.setEmails(emails);

        return (Client)userService.registerNewUser(client);
    }

    public Guest createGuest(){
        Guest guest=new Guest();
        guest.setName("Selahattin");
        guest.setLastName("Şahintaş");
        guest.setActive(true);
        guest.setIdentifierNumber("22334455667");
        guest.setUsername("selahattin");
        guest.setPassword("a2m8127mta");
        guest.setRegistrationDate(new Date());

        Collection<Privilege> privileges = new ArrayList<>();
        Privilege privilege=new Privilege();
        privilege.setName("READ");
        privilege.setDescription("Read Privilige");
        privileges.add(privilege);

        Collection<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName("ROLE_GUEST");
        role.setPrivileges(privileges);
        roles.add(role);
        guest.setRoles(roles);

        guest.setBirthPlace("Erzincan");
        guest.setEducation("Üniversite");
        guest.setJob("Yazılımcı");

        return (Guest) userService.registerNewUser(guest);
    }

    public void createAffinityBetweenUsers(Client client,Guest guest){
        Collection<Guest> guests=new ArrayList<>();
        guests.add(guest);
        client.setGuests(guests);

    }


}
