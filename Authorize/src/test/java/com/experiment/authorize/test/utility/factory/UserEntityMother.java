package com.experiment.authorize.test.utility.factory;

import com.experiment.authorize.entity.UserComponentContentEntity;
import com.experiment.authorize.entity.UserComponentEntity;
import com.experiment.authorize.entity.auth.RoleEntity;
import com.experiment.authorize.entity.auth.UserEntity;
import com.experiment.authorize.entity.base.BaseEntity;
import com.experiment.authorize.repository.base.EntityRepository;

import java.util.ArrayList;
import java.util.List;

public class UserEntityMother {
    private UserEntity userEntity = new UserEntity();

    public static UserEntityMother builder(){
        return new UserEntityMother();
    }

    public UserEntityMother complete(EntityRepository<BaseEntity> entityRepository){
        RoleEntity role = entityRepository.save(RoleEntityMother.builder().complete().build());
        UserComponentEntity userComponent = entityRepository.save(UserComponentEntityMother.builder().complete().build());
        userEntity = this
                .complete()
                .role(role)
                .content(UserComponentContentEntityMother.builder().complete().component(userComponent).build())
                .build();
        return this;
    }

    public UserEntityMother complete(){
        userEntity.setName("name");
        userEntity.setLastName("lastname");
        userEntity.setUsername("username");
        userEntity.setPassword("password");
        userEntity.setActive(true);
        return this;
    }

    public UserEntityMother role(RoleEntity roleEntity){
        userEntity.addRole(roleEntity);
        return this;
    }

    public UserEntityMother content(UserComponentContentEntity userComponentContentEntity){
        userEntity.addUserComponentContent(userComponentContentEntity);
        return this;
    }

    public UserEntity build(){
        return userEntity;
    }

    public static List<UserEntity> generateRandom(int count){
        List<UserEntity> userEntities = new ArrayList<>();
        for(int i = 0;i<count;i++){
            UserEntity userEntity = builder().complete().build();
            userEntity.setUsername(userEntity.getUsername()+i);
            userEntity.setName(userEntity.getName()+i);
            userEntity.setLastName(userEntity.getLastName()+i);
            userEntity.setPassword(userEntity.getPassword()+i);
            userEntities.add(userEntity);
        }
        return userEntities;
    }

}
