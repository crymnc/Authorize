package com.anatoliapark.nursinghome.entity.webpage;

import com.anatoliapark.nursinghome.entity.auth.AuthorityOptionEntity;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name="webpage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebPageEntity extends BaseConstantEntity {

    @OneToMany(
            mappedBy = "webPage",
            orphanRemoval = true,
            cascade=CascadeType.REMOVE
    )
    private Collection<WebPageComponentEntity> webPageComponents;

    @OneToMany(
            mappedBy = "webPage",
            orphanRemoval = true,
            cascade=CascadeType.REMOVE
    )
    private Collection<AuthorityOptionEntity> authorityOptions;
}
