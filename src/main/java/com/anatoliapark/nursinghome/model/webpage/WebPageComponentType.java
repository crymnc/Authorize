package com.anatoliapark.nursinghome.model.webpage;

import com.anatoliapark.nursinghome.model.base.BaseConstantEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "webpage_component_type")
public class WebPageComponentType extends BaseConstantEntity {

    @OneToMany(
            mappedBy = "componentType",
            orphanRemoval = true,
            cascade=CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Collection<WebPageComponent> webPageComponents;

    public Collection<WebPageComponent> getWebPageComponents() {
        return webPageComponents;
    }

    public void setWebPageComponents(Collection<WebPageComponent> webPageComponents) {
        this.webPageComponents = webPageComponents;
    }
}
