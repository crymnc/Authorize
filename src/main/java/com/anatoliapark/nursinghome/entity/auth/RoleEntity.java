package com.anatoliapark.nursinghome.entity.auth;

import com.anatoliapark.nursinghome.entity.UserComponentEntity;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public void addAuthorityGroup(AuthorityGroupEntity authorityGroupEntity){
        if(this.getAuthorityGroups() == null)
            this.setAuthorityGroups(new HashSet<>());
        this.getAuthorityGroups().add(authorityGroupEntity);
    }

    public void addUser(UserEntity userEntity){
        this.users.add(userEntity);
    }

    public void addComponent(UserComponentEntity userComponentEntity){
        if(this.getUserComponents() == null)
            this.setUserComponents(new HashSet<>());
        this.getUserComponents().add(userComponentEntity);
    }
}
