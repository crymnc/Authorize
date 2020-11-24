package com.anatoliapark.nursinghome.controller.rest;

import com.anatoliapark.nursinghome.annotation.RestApiController;
import com.anatoliapark.nursinghome.entity.auth.RoleEntity;
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


    @PostMapping("/register")
    public String registerNewUser(@RequestBody User user){
        User availableUser = userService.findUserByUsername(user.getUsername());
        if(availableUser == null){
            userService.registerNewUser(user);
        }
        else{
            throw new UserAlreadyExistException("UserEntity already exists");
        }
        return "Success";
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/roles")
    public List<RoleEntity> getRoles(){
        return constantService.findAll(RoleEntity.class);
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userService.findAllUsers();
    }
}
