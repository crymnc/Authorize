package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.domain.User;
import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.mapper.UserMapper;
import com.anatoliapark.nursinghome.repository.UserRepository;
import com.anatoliapark.nursinghome.repository.base.EntityRepository;
import com.anatoliapark.nursinghome.service.base.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends EntityService<UserEntity> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;


    @Transactional
    public User saveUser(User newUser){
        if(newUser.getPassword() != null)
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return null;
    }

    @Transactional
    public User updateUser(User newUser){
        UserEntity oldUser = userRepository.findById(newUser.getId()).get();
        UserEntity savedUserEntity = userRepository.save(oldUser);
        return null;
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public void deleteUserByUsername(String username){
        userRepository.deleteByUsername(username);
    }

    public User findUserByUsername(String username){
        UserEntity userEntity  = userRepository.findByUsername(username);
        if(userEntity == null)
            return null;
        User user = userMapper.toDomain(userEntity);
        return user;
    }

    public User findUserById(Long id){
        UserEntity userEntity = userRepository.getOne(id);
        if(userEntity == null)
            return null;
        User user = userMapper.toDomain(userEntity);
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
        List<User> users = new ArrayList<>();
        userEntities.stream().forEach(userEntity -> users.add(userMapper.toDomain(userEntity)));
        return users;
    }
}
