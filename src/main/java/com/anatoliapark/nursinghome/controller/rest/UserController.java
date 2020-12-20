package com.anatoliapark.nursinghome.controller.rest;

import com.anatoliapark.nursinghome.annotation.RestApiController;
import com.anatoliapark.nursinghome.exception.BussinessException;
import com.anatoliapark.nursinghome.exception.UserAlreadyExistException;
import com.anatoliapark.nursinghome.model.User;
import com.anatoliapark.nursinghome.repository.base.EntityRepository;
import com.anatoliapark.nursinghome.service.ConstantService;
import com.anatoliapark.nursinghome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestApiController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConstantService constantService;

    @Autowired
    private EntityRepository entityRepository;


    @PostMapping("/save")
    public String registerNewUser(@RequestBody User user){
        User availableUser = userService.findUserByUsername(user.getUsername());
        if(availableUser == null){
            userService.saveUser(user);
        }
        else{
            throw new UserAlreadyExistException("UserEntity already exists");
        }
        return "Success";
    }

    @PutMapping("/save")
    public String updateUser(@RequestBody User user){
        User availableUser = userService.findUserByUsername(user.getUsername());
        if(availableUser != null){
            userService.saveUser(user);
        }
        else{
            throw new BussinessException("User is not found to update. Firstly, save user");
        }
        return "Success";
    }

    @DeleteMapping(params = {"username"})
    public void deleteUserByUsername(@RequestParam("username") String username){
        userService.deleteUserByUsername(username);
    }

    @DeleteMapping(params = {"id"})
    public void deleteUserById(@RequestParam("id") Long id){
        userService.deleteUserById(id);
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userService.findAllUsers();
    }
}
