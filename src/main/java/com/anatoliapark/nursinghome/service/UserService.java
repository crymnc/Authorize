package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.domain.User;
import com.anatoliapark.nursinghome.entity.UserComponentContentEntity;
import com.anatoliapark.nursinghome.entity.UserComponentEntity;
import com.anatoliapark.nursinghome.entity.auth.RoleEntity;
import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.exception.BussinessException;
import com.anatoliapark.nursinghome.exception.UserAlreadyExistException;
import com.anatoliapark.nursinghome.repository.UserRepository;
import com.anatoliapark.nursinghome.service.base.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends EntityService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConstantService constantService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserEntity saveUser(UserEntity newUser){
        findUserByUsername(newUser.getUsername()).orElseThrow(() -> new UserAlreadyExistException("UserEntity already exists"));
        if(newUser.getPassword() != null)
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        UserEntity savedUserEntity = userRepository.save(newUser);
        return savedUserEntity;
    }

    @Transactional
    public UserEntity updateUser(User newUser){
        UserEntity availableUserEntity = findUserById(newUser.getId()).orElseThrow(() -> new BussinessException("User is not found to update. Firstly, save user"));
        prepareToUpdate(availableUserEntity,newUser);
        UserEntity savedUserEntity = userRepository.save(availableUserEntity);
        return savedUserEntity;
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public void deleteUserByUsername(String username){
        userRepository.deleteByUsername(username);
    }

    public Optional<UserEntity> findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<UserEntity> findUserById(Long id){
        return userRepository.findById(id);
    }

    public Optional<UserEntity> findLoggedUser(){
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        String username = ((UserDetails)userDetails).getUsername();
        return findUserByUsername(username);
    }

    public List<UserEntity> findAllUsers(){
        return findAll(UserEntity.class);
    }

    private void prepareToUpdate(UserEntity oldUserEntity, User newUser){
        oldUserEntity.setName(newUser.getName());
        oldUserEntity.setLastName(newUser.getLastName());
        oldUserEntity.setActive(newUser.isActive());
        if(newUser.getPassword() != null)
            oldUserEntity.setPassword(newUser.getPassword());
        newUser.getRoles().forEach(roleId -> {
            RoleEntity roleEntity = constantService.find(roleId, RoleEntity.class).get();
            oldUserEntity.getRoles().add(roleEntity);
        });
        newUser.getUserComponentContents().forEach(userComponentContent -> {
            UserComponentContentEntity userComponentContentEntity;
            if(userComponentContent.getId() != null){
                userComponentContentEntity = constantService.find(userComponentContent.getId(), UserComponentContentEntity.class).get();
            }
            else{
                userComponentContentEntity = new UserComponentContentEntity();
            }
            UserComponentEntity userComponentEntity = constantService.find(userComponentContent.getComponentId(), UserComponentEntity.class).get();
            userComponentContentEntity.setComponent(userComponentEntity);
            userComponentContentEntity.setContent(userComponentContent.getContent());
            oldUserEntity.addUserComponentContent(userComponentContentEntity);
        });
    }
}
