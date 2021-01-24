package com.anatoliapark.nursinghome.controller.rest;

import com.anatoliapark.nursinghome.annotation.RestApiController;
import com.anatoliapark.nursinghome.domain.User;
import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.exception.BussinessException;
import com.anatoliapark.nursinghome.exception.UserAlreadyExistException;
import com.anatoliapark.nursinghome.manager.UserManager;
import com.anatoliapark.nursinghome.mapper.UserMapper;
import com.anatoliapark.nursinghome.service.ConstantService;
import com.anatoliapark.nursinghome.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestApiController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final ConstantService constantService;

    private final UserManager userManager;

    private final UserMapper userMapper;

    public UserController(UserService userService, ConstantService constantService, UserManager userManager, UserMapper userMapper) {
        this.userService = userService;
        this.constantService = constantService;
        this.userManager = userManager;
        this.userMapper = userMapper;
    }


    @PostMapping("/save")
    public String registerNewUser(@RequestBody User user){
        UserEntity availableUser = userService.findUserByUsername(user.getUsername());
        if(availableUser == null){
            userService.saveUser(userMapper.toEntity(user));
        }
        else{
            throw new UserAlreadyExistException("UserEntity already exists");
        }
        return "Success";
    }

    @PutMapping("/save")
    public String updateUser(@RequestBody User user){
        UserEntity availableUser = userService.findUserById(user.getId());
        if(availableUser != null){
            userManager.getUpdatedUserEntity(availableUser,user);
            userService.updateUser(availableUser);
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
        return userMapper.toDomainList(userService.findAllUsers());
    }
}
