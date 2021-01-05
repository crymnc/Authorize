package com.anatoliapark.nursinghome.entity.auth;

import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="authority")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityEntity extends BaseConstantEntity{

    @OneToMany(
            mappedBy = "authority",
            orphanRemoval = true,
            cascade=CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    private Set<AuthorityOptionEntity> authorityOptions;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private Set<AuthorityGroupEntity> authorityGroups = new HashSet<>();

    public void addAuthorityGroup(AuthorityGroupEntity authorityGroupEntity){
        this.authorityGroups.add(authorityGroupEntity);
    }   public Set<AuthorityOptionEntity> getAuthorityOptions() {
        return authorityOptions;
    }

}
