package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.entity.UserComponentContentEntity;
import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.repository.UserRepository;
import com.anatoliapark.nursinghome.service.base.EntityService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService extends EntityService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserEntity saveUser(UserEntity newUser){
        if(newUser.getPassword() != null)
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        Set<UserComponentContentEntity> userComponentContentEntitySet = new HashSet<>();
        newUser.getUserComponentContents().stream().forEach(newUserComponentContentEntity -> {
            newUserComponentContentEntity.setUser(newUser);
            userComponentContentEntitySet.add(newUserComponentContentEntity);
        });
        newUser.setUserComponentContents(userComponentContentEntitySet);
        UserEntity savedUserEntity = userRepository.save(newUser);
        return savedUserEntity;
    }

    @Transactional
    public UserEntity updateUser(UserEntity newUser){
        if(newUser.getPassword() != null)
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        UserEntity savedUserEntity = userRepository.save(newUser);
        return savedUserEntity;
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public void deleteUserByUsername(String username){
        userRepository.deleteByUsername(username);
    }

    public UserEntity findUserByUsername(String username){
        UserEntity userEntity  = userRepository.findByUsername(username);
        if(userEntity == null)
            return null;
        return userEntity;
    }

    public UserEntity findUserById(Long id){
        UserEntity userEntity = userRepository.findById(id).get();
        if(userEntity == null)
            return null;
        return userEntity;
    }

    public UserEntity findLoggedUser(){
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            String username = ((UserDetails)userDetails).getUsername();
            return findUserByUsername(username);
        }
        return null;
    }

    public List<UserEntity> findAllUsers(){
        return findAll(UserEntity.class);
    }
}
