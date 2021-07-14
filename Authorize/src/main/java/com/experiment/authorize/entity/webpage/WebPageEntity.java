package com.experiment.authorize.entity.webpage;

import com.experiment.authorize.entity.auth.AuthorityOptionEntity;
import com.experiment.authorize.entity.base.BaseConstantEntity;
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
