package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.domain.User;
import com.anatoliapark.nursinghome.entity.UserComponentContentEntity;
import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.mapper.UserMapper;
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

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }


    public User saveUser(User newUser){
        if(newUser.getPassword() != null)
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        UserEntity newUserEntity = userMapper.toEntity(newUser);
        Set<UserComponentContentEntity> userComponentContentEntitySet = new HashSet<>();
        newUserEntity.getUserComponentContents().stream().forEach(newUserComponentContentEntity -> {
            newUserComponentContentEntity.setUser(newUserEntity);
            userComponentContentEntitySet.add(newUserComponentContentEntity);
        });
        newUserEntity.setUserComponentContents(userComponentContentEntitySet);
        UserEntity savedUserEntity = userRepository.save(newUserEntity);
        return userMapper.toDomain(savedUserEntity);
    }

    @Transactional
    public User updateUser(User newUser){
        UserEntity oldUser = userRepository.findById(newUser.getId()).get();
        UserEntity savedUserEntity = userRepository.save(oldUser);
        return userMapper.toDomain(savedUserEntity);
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
        List<UserEntity> userEntities = findAll(UserEntity.class);
        List<User> users = userMapper.toDomainList(userEntities);
        return users;
    }
}
