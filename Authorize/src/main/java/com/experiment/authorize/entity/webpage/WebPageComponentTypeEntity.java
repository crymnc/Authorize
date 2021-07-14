package com.experiment.authorize.entity.webpage;

import com.experiment.authorize.entity.base.BaseConstantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "webpage_component_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebPageComponentTypeEntity extends BaseConstantEntity {

    @OneToMany(
            mappedBy = "componentType",
            orphanRemoval = true,
            cascade=CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private Collection<WebPageComponentEntity> webPageComponents;
}
