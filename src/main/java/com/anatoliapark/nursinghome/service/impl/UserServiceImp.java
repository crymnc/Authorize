package com.anatoliapark.nursinghome.service.impl;

import com.anatoliapark.nursinghome.exception.UserAlreadyExistException;
import com.anatoliapark.nursinghome.model.base.User;
import com.anatoliapark.nursinghome.repository.PrivilegeRepository;
import com.anatoliapark.nursinghome.repository.RoleRepository;
import com.anatoliapark.nursinghome.repository.UserRepository;
import com.anatoliapark.nursinghome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getActiveUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User registerNewUser(User user) {
        User registeredUser = userRepository.findByUsername(user.getUsername());
        if (registeredUser != null) {
            throw new UserAlreadyExistException("This username already taken. Username: "+user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
