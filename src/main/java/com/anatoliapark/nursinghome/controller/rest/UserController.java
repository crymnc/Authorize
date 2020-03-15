package com.anatoliapark.nursinghome.controller.rest;

import com.anatoliapark.nursinghome.model.auth.Role;
import com.anatoliapark.nursinghome.model.auth.User;
import com.anatoliapark.nursinghome.repository.ConstantRepository;
import com.anatoliapark.nursinghome.repository.EntityRepository;
import com.anatoliapark.nursinghome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConstantRepository constantRepository;

    @Autowired
    private EntityRepository entityRepository;


    @PostMapping("/register")
    public void registerNewUser(@RequestBody User user){

        userService.registerNewUser(user);
    }

    @GetMapping("/register")
    public Collection<Role> registerNewUser(){
        return constantRepository.findAll(Role.class);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAllUsers();
    }
}
