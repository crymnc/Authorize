package com.anatoliapark.nursinghome.entity.auth;

import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.model.AuthorityGroup;
import com.anatoliapark.nursinghome.util.Mapper;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "authority_group")
@ModelMapping(modelClass = AuthorityGroup.class)
public class AuthorityGroupEntity extends BaseConstantEntity {

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH,CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(
            name = "authoritygroup_authority",
            joinColumns = {@JoinColumn(name = "authority_group_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")}
    )
    private Set<AuthorityEntity> authorities;

    @ManyToMany(mappedBy = "authorityGroups", fetch = FetchType.LAZY)
    private Set<RoleEntity> roles = new HashSet<>();

    public AuthorityGroupEntity(AuthorityGroup authorityGroup) {
        super(authorityGroup);
        Set<AuthorityEntity> authorityEntities = Mapper.getEntitySet(authorityGroup.getAuthorities());
        authorityEntities.forEach(authorityEntity -> authorityEntity.addAuthorityGroup(this));
        this.setAuthorities(authorityEntities);
    }

    public AuthorityGroupEntity(){}

    public Collection<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void addRole(RoleEntity roleEntity){
        this.roles.add(roleEntity);
    }

}
