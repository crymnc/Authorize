package com.anatoliapark.nursinghome.controller.rest;

import com.anatoliapark.nursinghome.annotation.RestApiController;
import com.anatoliapark.nursinghome.model.auth.Role;
import com.anatoliapark.nursinghome.model.auth.User;
import com.anatoliapark.nursinghome.repository.ConstantRepository;
import com.anatoliapark.nursinghome.repository.EntityRepository;
import com.anatoliapark.nursinghome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestApiController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConstantRepository constantRepository;

    @Autowired
    private EntityRepository entityRepository;


    @PostMapping("/register")
    public String registerNewUser(@RequestBody User user){
        userService.registerNewUser(user);
        return "Success";
    }

    @GetMapping("/roles")
    public List<Role> getRoles(){
        return constantRepository.findAll(Role.class);
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userService.findAllUsers();
    }
}
