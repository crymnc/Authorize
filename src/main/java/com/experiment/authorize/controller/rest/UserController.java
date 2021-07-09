package com.experiment.authorize.controller.rest;

import com.experiment.authorize.annotation.RestApiController;
import com.experiment.authorize.domain.User;
import com.experiment.authorize.mapper.UserMapper;
import com.experiment.authorize.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*" , methods = {RequestMethod.POST,RequestMethod.GET}, maxAge = 3600)
@RestApiController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/save")
    public String registerNewUser(@RequestBody User user){
        userService.saveUser(userMapper.toEntity(user));
        return "Success";
    }

    @PutMapping("/save")
    public String updateUser(@RequestBody User user){
        userService.updateUser(user);
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

    @GetMapping
    public List<User> getUsers() {
        return userMapper.toDomainList(userService.findAllUsers());
    }
}
