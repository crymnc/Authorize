package com.experiment.authorize.test.utility.factory;

import com.experiment.authorize.entity.auth.RoleEntity;

public class RoleEntityMother {

    private RoleEntity roleEntity = new RoleEntity();

    public static RoleEntityMother builder(){
        return new RoleEntityMother();
    }

    private RoleEntityMother(){}

    public RoleEntityMother complete(){
        roleEntity.setName("role name");
        roleEntity.setDsc("role desc");
        return this;
    }

    public RoleEntity build(){
        return roleEntity;
    }
}
