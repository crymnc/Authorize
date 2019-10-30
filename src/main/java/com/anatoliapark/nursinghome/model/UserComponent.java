package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.model.auth.UserType;
import com.anatoliapark.nursinghome.model.base.BaseConstantEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user_component")
public class UserComponent extends BaseConstantEntity {

    @ManyToMany(mappedBy = "userComponents", fetch = FetchType.LAZY)
    private Collection<UserType> userTypes;

    @OneToMany(
            mappedBy = "component",
            orphanRemoval = true,
            cascade=CascadeType.ALL
    )
    private Collection<UserComponentContent> userComponentContents;

    public Collection<UserType> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(Collection<UserType> userTypes) {
        this.userTypes = userTypes;
    }

    public Collection<UserComponentContent> getUserComponentContents() {
        return userComponentContents;
    }

    public void setUserComponentContents(Collection<UserComponentContent> userComponentContents) {
        this.userComponentContents = userComponentContents;
    }
}
