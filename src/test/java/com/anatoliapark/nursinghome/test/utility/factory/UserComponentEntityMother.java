package com.anatoliapark.nursinghome.test.utility.factory;

import com.anatoliapark.nursinghome.entity.UserComponentEntity;

public class UserComponentEntityMother {

    private UserComponentEntity componentEntity = new UserComponentEntity();

    public static UserComponentEntityMother builder(){
        return new UserComponentEntityMother();
    }

    public UserComponentEntityMother complete(){
        componentEntity.setName("component");
        componentEntity.setDsc("component dsc");
        return this;
    }

    public UserComponentEntity build(){
        return componentEntity;
    }
}
