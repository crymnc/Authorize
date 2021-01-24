package com.anatoliapark.nursinghome.manager;

import com.anatoliapark.nursinghome.domain.User;
import com.anatoliapark.nursinghome.entity.UserComponentContentEntity;
import com.anatoliapark.nursinghome.entity.UserComponentEntity;
import com.anatoliapark.nursinghome.entity.auth.RoleEntity;
import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.service.ConstantService;
import org.springframework.stereotype.Component;

@Component
public class UserManager implements Manager{
    final private ConstantService constantService;

    public UserManager(ConstantService constantService) {
        this.constantService = constantService;
    }

    public void getUpdatedUserEntity(UserEntity oldUserEntity, User newUser){
        oldUserEntity.setName(newUser.getName());
        oldUserEntity.setLastName(newUser.getLastName());
        oldUserEntity.setActive(newUser.isActive());
        if(newUser.getPassword() != null)
            oldUserEntity.setPassword(newUser.getPassword());
        oldUserEntity.getRoles().clear();
        newUser.getRoles().forEach(role -> {
            RoleEntity roleEntity = constantService.find(role.getId(), RoleEntity.class);
            oldUserEntity.getRoles().add(roleEntity);
        });
        oldUserEntity.getUserComponentContents().clear();
        newUser.getUserComponentContents().forEach(userComponentContent -> {
            UserComponentContentEntity userComponentContentEntity;
            if(userComponentContent.getId() != null){
                userComponentContentEntity = constantService.find(userComponentContent.getId(), UserComponentContentEntity.class);
            }
            else{
                userComponentContentEntity = new UserComponentContentEntity();
            }
            UserComponentEntity userComponentEntity = constantService.find(userComponentContent.getComponentId(), UserComponentEntity.class);
            userComponentContentEntity.setComponent(userComponentEntity);
            userComponentContentEntity.setContent(userComponentContent.getContent());
            oldUserEntity.addUserComponentContent(userComponentContentEntity);
        });
    }
}
