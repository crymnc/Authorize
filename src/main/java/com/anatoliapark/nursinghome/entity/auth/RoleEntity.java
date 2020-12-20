package com.anatoliapark.nursinghome.entity.auth;

import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.entity.UserComponentEntity;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.model.Role;
import com.anatoliapark.nursinghome.util.Mapper;

import javax.persistence.*;
import java.util.HashSet;
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
    private Set<UserEntity> users = new HashSet<>();

    public RoleEntity(Role role) {
        super(role);
        Set<AuthorityGroupEntity> authorityGroupEntities = Mapper.getEntitySet(role.getAuthorityGroups());
        authorityGroupEntities.forEach(authorityGroupEntity -> authorityGroupEntity.addRole(this));
        this.setAuthorityGroups(authorityGroupEntities);
        Set<UserComponentEntity> userComponentEntities = Mapper.getEntitySet(role.getUserComponents());
        userComponentEntities.forEach(userComponentEntity -> userComponentEntity.addRole(this));
        this.setUserComponents(userComponentEntities);
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

    public void addUser(UserEntity userEntity){
        this.users.add(userEntity);
    }

    public Set<UserComponentEntity> getUserComponents() {
        return userComponents;
    }

    public void setUserComponents(Set<UserComponentEntity> userComponents) {
        this.userComponents = userComponents;
    }
}
