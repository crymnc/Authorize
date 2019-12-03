package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.exception.UserAlreadyExistException;
import com.anatoliapark.nursinghome.model.auth.User;
import com.anatoliapark.nursinghome.repository.EntityRepository;
import com.anatoliapark.nursinghome.repository.impl.ConstantRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private EntityRepository userRepository;

    @Autowired
    private ConstantRepositoryImpl constantRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;





}
