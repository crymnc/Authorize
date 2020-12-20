package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.model.User;
import com.anatoliapark.nursinghome.repository.UserRepository;
import com.anatoliapark.nursinghome.repository.base.EntityRepository;
import com.anatoliapark.nursinghome.service.base.EntityService;
import com.anatoliapark.nursinghome.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends EntityService<UserEntity> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User saveUser(User newUser){
        if(newUser.getPassword() != null)
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        UserEntity newUserEntity = Mapper.convertToEntity(newUser);
        UserEntity savedUserEntity = userRepository.save(newUserEntity);
        return Mapper.convertToModel(savedUserEntity);
    }

    public void deleteUserById(Long id){
        userRepository.delete(id);
    }

    public void deleteUserByUsername(String username){
        userRepository.deleteByUsername(username);
    }

    public User findUserByUsername(String username){
        UserEntity userEntity  = userRepository.findByUsername(username);
        if(userEntity == null)
            return null;
        User user = Mapper.convertToModel(userEntity);
        return user;

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
        List<UserEntity> userEntities = entityRepository.findAll(Example.of(new UserEntity()));
        return Mapper.getModelList(userEntities);
    }
}
