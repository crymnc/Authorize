package com.anatoliapark.nursinghome.entity.webpage;

import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.model.WebPageComponentType;
import com.anatoliapark.nursinghome.model.base.BaseModel;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "webpage_component_type")
@ModelMapping(modelClass = WebPageComponentType.class)
public class WebPageComponentTypeEntity extends BaseConstantEntity {

    @OneToMany(
            mappedBy = "componentType",
            orphanRemoval = true,
            cascade=CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private Collection<WebPageComponentEntity> webPageComponents;

    public WebPageComponentTypeEntity(BaseModel model) {
        super(model);
    }

    public WebPageComponentTypeEntity(){}

    public Collection<WebPageComponentEntity> getWebPageComponents() {
        return webPageComponents;
    }

    public void setWebPageComponents(Collection<WebPageComponentEntity> webPageComponents) {
        this.webPageComponents = webPageComponents;
    }
}
