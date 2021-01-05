package com.anatoliapark.nursinghome.entity.auth;

import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "authority_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public void addAuthority(AuthorityEntity authorityEntity){
        if(this.getAuthorities() == null)
            this.setAuthorities(new HashSet<>());
        this.getAuthorities().add(authorityEntity);
    }

    public void addRole(RoleEntity roleEntity){
        this.roles.add(roleEntity);
    }

}
