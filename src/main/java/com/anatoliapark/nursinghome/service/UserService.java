package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.model.User;
import com.anatoliapark.nursinghome.repository.EntityRepository;
import com.anatoliapark.nursinghome.util.Mapper;
import org.springframework.beans.BeanUtils;
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
    private PasswordEncoder passwordEncoder;


    public UserEntity registerNewUser(User newUser){
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        UserEntity newUserEntity = new UserEntity();
        BeanUtils.copyProperties(newUser, newUserEntity);
        entityRepository.save(newUser);
        return newUserEntity;
    }

    public void deleteUser(Long id){
        entityRepository.delete(id);
    }

    public User findUserByUsername(String username){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity = (UserEntity) entityRepository.findOne(Example.of(userEntity));
        return userEntity.getModal();
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
