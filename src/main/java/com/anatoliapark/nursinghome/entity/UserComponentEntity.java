package com.anatoliapark.nursinghome.entity;

import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.entity.auth.RoleEntity;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.model.UserComponent;
import com.anatoliapark.nursinghome.util.Mapper;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "user_component")
@ModelMapping(modelClass = UserComponent.class)
public class UserComponentEntity extends BaseConstantEntity {

    @ManyToMany(mappedBy = "userComponents", fetch = FetchType.LAZY)
    private Set<RoleEntity> roles;

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

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public Set<UserComponentContentEntity> getUserComponentContents() {
        if(Hibernate.isInitialized(userComponentContents))
            return userComponentContents;
        return null;
    }

    public void setUserComponentContents(Set<UserComponentContentEntity> userComponentContents) {
        this.userComponentContents = userComponentContents;
    }

}
