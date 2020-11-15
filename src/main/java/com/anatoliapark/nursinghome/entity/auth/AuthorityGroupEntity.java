package com.anatoliapark.nursinghome.entity.auth;

import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.model.AuthorityGroup;
import com.anatoliapark.nursinghome.util.Mapper;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity(name = "authority_group")
@ModelMapping(modelClass = AuthorityGroup.class)
public class AuthorityGroupEntity extends BaseConstantEntity {

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinTable(
            name = "authoritygroup_authority",
            joinColumns = {@JoinColumn(name = "authority_group_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")}
    )
    private Set<AuthorityEntity> authorities;

    public AuthorityGroupEntity(AuthorityGroup authorityGroup) {
        super(authorityGroup);
        this.setAuthorities(Mapper.getEntitySet(authorityGroup.getAuthorities()));
    }

    public AuthorityGroupEntity(){}

    public Collection<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }


}
