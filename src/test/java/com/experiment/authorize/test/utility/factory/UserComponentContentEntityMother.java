package com.experiment.authorize.test.utility.factory;

import com.experiment.authorize.entity.UserComponentContentEntity;
import com.experiment.authorize.entity.UserComponentEntity;

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

