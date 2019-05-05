package com.anatoliapark.nursinghome.controller;

import com.anatoliapark.nursinghome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(Principal principal) {

        if (principal != null) {
            return "redirect:/home";
        }
        return "/login";
    }

}
