package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.annotation.EntityMapping;
import com.anatoliapark.nursinghome.entity.auth.AuthorityGroupEntity;
import com.anatoliapark.nursinghome.model.base.BaseConstantModel;
import com.anatoliapark.nursinghome.util.Mapper;

import java.util.List;

@EntityMapping(entityClass = AuthorityGroupEntity.class)
public class AuthorityGroup extends BaseConstantModel {

    private List<Authority> authorities;

    public AuthorityGroup(AuthorityGroupEntity authorityGroupEntity) {
        super(authorityGroupEntity);
        this.setAuthorities(Mapper.getModelList(authorityGroupEntity.getAuthorities()));
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
