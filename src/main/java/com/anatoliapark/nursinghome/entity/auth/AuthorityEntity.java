package com.anatoliapark.nursinghome.entity.auth;

import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.model.Authority;
import com.anatoliapark.nursinghome.util.Mapper;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity(name="authority")
@ModelMapping(modelClass = Authority.class)
public class AuthorityEntity extends BaseConstantEntity{

    @OneToMany(
            mappedBy = "authority",
            orphanRemoval = true,
            cascade=CascadeType.REMOVE
    )
    private Collection<AuthorityOptionEntity> authorityOptions;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private Set<AuthorityGroupEntity> authorityGroups;

    public AuthorityEntity(Authority authority) {
        super(authority);
        this.setAuthorityOptions(Mapper.getEntityList(authority.getAuthorityOptions()));
    }

    public AuthorityEntity(){}

    public Collection<AuthorityOptionEntity> getAuthorityOptions() {
        return authorityOptions;
    }

    public void setAuthorityOptions(Collection<AuthorityOptionEntity> authorityOptions) {
        this.authorityOptions = authorityOptions;
    }

    public Set<AuthorityGroupEntity> getAuthorityGroups() {
        return authorityGroups;
    }
}
