package com.anatoliapark.nursinghome.controller.rest;

import com.anatoliapark.nursinghome.model.auth.User;
import com.anatoliapark.nursinghome.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegistrationController {

    @Autowired
    private AuthenticationService userService;


    @PostMapping("/register")
    public void registerNewUser(){
        User user = new User();
        user.setName("Can");
        user.setLastName("Şahintaş");
        user.setUsername("crymnc");
        user.setPassword("a2m8z7mta");

        userService.registerNewUser(user);
    }
}
