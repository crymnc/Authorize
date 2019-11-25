package com.anatoliapark.nursinghome.model.auth;

import com.anatoliapark.nursinghome.model.base.BaseConstantEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="authority_group")
public class AuthorityGroup extends BaseConstantEntity {

    @ManyToMany
    @JoinTable(
            name = "authoritygroup_authority",
            joinColumns = {@JoinColumn(name = "authority_group_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")}
    )
    private Collection<Authority> authorities;

    @ManyToMany(mappedBy = "authorityGroups", fetch = FetchType.LAZY)
    private Collection<Role> roles;

    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

}
