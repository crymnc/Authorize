package com.anatoliapark.nursinghome.test.utility.factory;

import com.anatoliapark.nursinghome.entity.UserComponentContentEntity;
import com.anatoliapark.nursinghome.entity.UserComponentEntity;

public class UserComponentContentEntityMother {

    private UserComponentContentEntity contentEntity = new UserComponentContentEntity();
    public static UserComponentContentEntityMother builder(){
        return new UserComponentContentEntityMother();
    }

    public UserComponentContentEntityMother complete(){
        contentEntity.setContent("content");
        contentEntity.setComponent(UserComponentEntityMother.builder().complete().build());
        return this;
    }

    public UserComponentContentEntityMother component(UserComponentEntity component){
        contentEntity.setComponent(component);
        return this;
    }

    public UserComponentContentEntity build(){
        return contentEntity;
    }
}

