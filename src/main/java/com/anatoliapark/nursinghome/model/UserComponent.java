package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.model.auth.Role;
import com.anatoliapark.nursinghome.model.base.BaseConstantEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "user_component")
public class UserComponent extends BaseConstantEntity {

    @ManyToMany(mappedBy = "userComponents", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Role> roles;

    @OneToMany(
            mappedBy = "component",
            orphanRemoval = true,
            cascade=CascadeType.ALL
    )
    @JsonIgnore
    private Collection<UserComponentContent> userComponentContents;

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<UserComponentContent> getUserComponentContents() {
        return userComponentContents;
    }

    public void setUserComponentContents(Collection<UserComponentContent> userComponentContents) {
        this.userComponentContents = userComponentContents;
    }
}
