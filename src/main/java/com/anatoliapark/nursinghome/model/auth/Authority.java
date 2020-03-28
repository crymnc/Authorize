package com.anatoliapark.nursinghome.model.auth;

import com.anatoliapark.nursinghome.model.base.BaseConstantEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity(name="authority")
public class Authority extends BaseConstantEntity {

    @OneToMany(
            mappedBy = "authority",
            orphanRemoval = true,
            cascade=CascadeType.REMOVE
    )
    private Collection<AuthorityOption> authorityOptions;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<AuthorityGroup> authorityGroups;

    public Collection<AuthorityOption> getAuthorityOptions() {
        return authorityOptions;
    }

    public void setAuthorityOptions(Collection<AuthorityOption> authorityOptions) {
        this.authorityOptions = authorityOptions;
    }

    public Collection<AuthorityGroup> getAuthorityGroups() {
        return authorityGroups;
    }

}
