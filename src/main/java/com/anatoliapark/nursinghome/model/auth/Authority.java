package com.anatoliapark.nursinghome.model.auth;

import com.anatoliapark.nursinghome.model.base.BaseConstantEntity;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="authority")
public class Authority extends BaseConstantEntity {

    @OneToMany(
            mappedBy = "authority",
            orphanRemoval = true,
            cascade=CascadeType.ALL
    )
    private Collection<AuthorityOption> authorityOptions;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
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

    public void setAuthorityGroups(Collection<AuthorityGroup> authorityGroups) {
        this.authorityGroups = authorityGroups;
    }
}
