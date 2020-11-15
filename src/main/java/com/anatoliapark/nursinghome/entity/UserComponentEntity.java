package com.anatoliapark.nursinghome.entity;

import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.entity.auth.RoleEntity;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.model.UserComponent;
import com.anatoliapark.nursinghome.util.Mapper;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity(name = "user_component")
@ModelMapping(modelClass = UserComponent.class)
public class UserComponentEntity extends BaseConstantEntity {

    @ManyToMany(mappedBy = "userComponents", fetch = FetchType.LAZY)
    private Collection<RoleEntity> roles;

    @OneToMany(
            mappedBy = "component",
            orphanRemoval = true,
            cascade=CascadeType.ALL, fetch = FetchType.LAZY
    )
    private Set<UserComponentContentEntity> userComponentContents;

    public UserComponentEntity(UserComponent userComponent) {
        super(userComponent);
        this.setUserComponentContents(Mapper.getEntitySet(userComponent.getContents()));
    }

    public UserComponentEntity(){}

    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }

    public Set<UserComponentContentEntity> getUserComponentContents() {
        return userComponentContents;
    }

    public void setUserComponentContents(Set<UserComponentContentEntity> userComponentContents) {
        this.userComponentContents = userComponentContents;
    }

}
