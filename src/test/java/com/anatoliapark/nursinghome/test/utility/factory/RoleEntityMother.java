package com.anatoliapark.nursinghome.test.utility.factory;

import com.anatoliapark.nursinghome.entity.auth.RoleEntity;

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
