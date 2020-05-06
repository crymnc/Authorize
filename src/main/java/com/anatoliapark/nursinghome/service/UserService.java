package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.model.auth.User;
import com.anatoliapark.nursinghome.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService {

    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private ConstantService constantService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registerNewUser(User newUser){
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        entityRepository.save(newUser);
        return newUser;
    }

    public void deleteUser(Long id){
        entityRepository.delete(id);
    }

    public User findUserByUsername(String username){
        User user = new User();
        user.setUsername(username);
        return (User) entityRepository.findOne(Example.of(user));
    }

    public User findLoggedUser(){
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            String username = ((UserDetails)userDetails).getUsername();
            return findUserByUsername(username);
        }
        return null;
    }

    public List<User> findAllUsers(){
        return entityRepository.findAll(Example.of(new User()));
    }
}
