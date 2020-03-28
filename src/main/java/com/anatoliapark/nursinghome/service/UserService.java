package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.model.auth.User;
import com.anatoliapark.nursinghome.repository.EntityRepository;
import com.anatoliapark.nursinghome.repository.impl.ConstantRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private ConstantRepositoryImpl constantRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registerNewUser(User newUser){
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        entityRepository.save(newUser);
        return newUser;
    }

    public User findUserBy(String username){
        HashMap<String,String> parameter = new HashMap();
        parameter.put("username",username);
        List<User> users = entityRepository.findBy(parameter, User.class);
        if(!users.isEmpty())
            return users.get(0);
        throw new UsernameNotFoundException("User not found");
    }

    public User findLoggedUser(){
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            String username = ((UserDetails)userDetails).getUsername();
            return findUserBy(username);
        }
        return null;
    }

    public List<User> findAllUsers(){
        return entityRepository.findAll(User.class);
    }
}
