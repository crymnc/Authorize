package com.anatoliapark.nursinghome.entity.webpage;

import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.model.WebPageComponent;
import com.anatoliapark.nursinghome.model.base.BaseModel;

import javax.persistence.*;

@Entity
@Table(name="webpage_component")
@ModelMapping(modelClass = WebPageComponent.class)
public class WebPageComponentEntity extends BaseConstantEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id", referencedColumnName = "id")
    private WebPageEntity webPage;

    @ManyToOne
    @JoinColumn(name = "component_type_id", referencedColumnName = "id")
    private WebPageComponentTypeEntity componentType;

    public WebPageComponentEntity(BaseModel model) {
        super(model);
    }

    public WebPageComponentEntity(){}

    public WebPageEntity getWebPage() {
        return webPage;
    }

    public void setWebPage(WebPageEntity webPage) {
        this.webPage = webPage;
    }

    public WebPageComponentTypeEntity getComponentType() {
        return componentType;
    }

    public void setComponentType(WebPageComponentTypeEntity componentType) {
        this.componentType = componentType;
    }

}
