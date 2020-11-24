package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.annotation.EntityMapping;
import com.anatoliapark.nursinghome.entity.auth.RoleEntity;
import com.anatoliapark.nursinghome.model.base.BaseConstantModel;
import com.anatoliapark.nursinghome.util.Mapper;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@EntityMapping(entityClass = RoleEntity.class)
public class Role extends BaseConstantModel {

    @JsonIgnore
    private List<AuthorityGroup> authorityGroups;
    @JsonIgnore
    private List<UserComponent> userComponents;

    public Role(RoleEntity roleEntity) {
        super(roleEntity);
        setUserComponents(Mapper.getModelList(roleEntity.getUserComponents()));
        setAuthorityGroups(Mapper.getModelList(roleEntity.getAuthorityGroups()));
    }

    public Role(){ super(); }

    public List<AuthorityGroup> getAuthorityGroups() {
        return authorityGroups;
    }

    public void setAuthorityGroups(List<AuthorityGroup> authorityGroups) {
        this.authorityGroups = authorityGroups;
    }

    public List<UserComponent> getUserComponents() {
        return userComponents;
    }

    public void setUserComponents(List<UserComponent> userComponents) {
        this.userComponents = userComponents;
    }
}
