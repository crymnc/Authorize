package com.anatoliapark.nursinghome.entity.auth;

import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.model.Authority;
import com.anatoliapark.nursinghome.util.Mapper;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="authority")
@ModelMapping(modelClass = Authority.class)
public class AuthorityEntity extends BaseConstantEntity{

    @OneToMany(
            mappedBy = "authority",
            orphanRemoval = true,
            cascade=CascadeType.REMOVE
    )
    private Set<AuthorityOptionEntity> authorityOptions;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private Set<AuthorityGroupEntity> authorityGroups = new HashSet<>();

    public AuthorityEntity(Authority authority) {
        super(authority);
        Set<AuthorityOptionEntity> authorityOptionEntities = Mapper.getEntitySet(authority.getAuthorityOptions());
        authorityOptionEntities.forEach(authorityOptionEntity -> authorityOptionEntity.setAuthority(this));
        this.setAuthorityOptions(authorityOptionEntities);
    }

    public AuthorityEntity(){}

    public Set<AuthorityOptionEntity> getAuthorityOptions() {
        return authorityOptions;
    }

    public void setAuthorityOptions(Set<AuthorityOptionEntity> authorityOptions) {
        this.authorityOptions = authorityOptions;
    }

    public Set<AuthorityGroupEntity> getAuthorityGroups() {
        return authorityGroups;
    }

    public void addAuthorityGroup(AuthorityGroupEntity authorityGroupEntity){
        this.authorityGroups.add(authorityGroupEntity);
    }
}
