package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.exception.UserAlreadyExistException;
import com.anatoliapark.nursinghome.model.auth.User;
import com.anatoliapark.nursinghome.repository.UserRepository;
import com.anatoliapark.nursinghome.repository.constant.impl.ConstantRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConstantRepositoryImpl constantRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getActiveUser(String username) {
        return userRepository.findByUsername(username);
    }

    public User registerNewUser(User user) {
        User registeredUser = userRepository.findByUsername(user.getUsername());
        if (registeredUser != null) {
            throw new UserAlreadyExistException("This username already taken. Username: "+user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
