package com.experiment.authorize.entity.webpage;

import com.experiment.authorize.entity.base.BaseConstantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="webpage_component")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebPageComponentEntity extends BaseConstantEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id", referencedColumnName = "id")
    private WebPageEntity webPage;

    @ManyToOne
    @JoinColumn(name = "component_type_id", referencedColumnName = "id")
    private WebPageComponentTypeEntity componentType;

}
