package com.anatoliapark.nursinghome.entity;

import com.anatoliapark.nursinghome.entity.auth.RoleEntity;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "user_component")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserComponentEntity extends BaseConstantEntity {

    @ManyToMany(mappedBy = "userComponents", fetch = FetchType.LAZY)
    private Set<RoleEntity> roles = new HashSet<>();

    @OneToMany(
            mappedBy = "component",
            orphanRemoval = true,
            cascade={CascadeType.REMOVE,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.LAZY
    )
    private Set<UserComponentContentEntity> userComponentContents;

    public void addRole(RoleEntity roleEntity) {
        this.roles.add(roleEntity);
    }

    public void addUserComponentContent(UserComponentContentEntity userComponentContentEntity){
        if(this.getUserComponentContents() == null)
            this.setUserComponentContents(new HashSet<>());
        this.getUserComponentContents().add(userComponentContentEntity);
    }

}
