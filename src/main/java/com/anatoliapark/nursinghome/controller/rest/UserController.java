package com.anatoliapark.nursinghome.controller.rest;

import com.anatoliapark.nursinghome.annotation.RestApiController;
import com.anatoliapark.nursinghome.exception.UserAlreadyExistException;
import com.anatoliapark.nursinghome.model.auth.Role;
import com.anatoliapark.nursinghome.model.auth.User;
import com.anatoliapark.nursinghome.repository.ConstantRepository;
import com.anatoliapark.nursinghome.repository.EntityRepository;
import com.anatoliapark.nursinghome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestApiController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConstantRepository constantRepository;

    @Autowired
    private EntityRepository entityRepository;


    @PostMapping("/register")
    public String registerNewUser(@RequestBody User user){
        HashMap<String,String> parameter = new HashMap();
        parameter.put("username",user.getUsername());
        List<User> users = entityRepository.findBy(parameter, User.class);
        if(CollectionUtils.isEmpty(users)){
            userService.registerNewUser(user);
        }
        else{
            throw new UserAlreadyExistException("User already exists");
        }
        return "Success";
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
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
