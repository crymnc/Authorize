package com.anatoliapark.nursinghome.entity.auth;

import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.entity.UserComponentEntity;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.model.Role;
import com.anatoliapark.nursinghome.util.Mapper;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "role")
@ModelMapping(modelClass = Role.class)
public class RoleEntity extends BaseConstantEntity {

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH,CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_authoritygroup",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_group_id", referencedColumnName = "id")}
    )
    private Set<AuthorityGroupEntity> authorityGroups;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH,CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_component",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "component_id", referencedColumnName = "id")}
    )
    private Set<UserComponentEntity> userComponents;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<UserEntity> users;

    public RoleEntity(Role role) {
        super(role);
        this.setAuthorityGroups(Mapper.getEntitySet(role.getAuthorityGroups()));
        this.setUserComponents(Mapper.getEntitySet(role.getUserComponents()));
    }

    public RoleEntity(){}

    public Set<AuthorityGroupEntity> getAuthorityGroups() {
        return authorityGroups;
    }

    public void setAuthorityGroups(Set<AuthorityGroupEntity> authorityGroups) {
        this.authorityGroups = authorityGroups;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public Set<UserComponentEntity> getUserComponents() {
        return userComponents;
    }

    public void setUserComponents(Set<UserComponentEntity> userComponents) {
        this.userComponents = userComponents;
    }


}
