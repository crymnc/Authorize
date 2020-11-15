package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.annotation.EntityMapping;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.entity.webpage.WebPageComponentEntity;
import com.anatoliapark.nursinghome.model.base.BaseConstantModel;

@EntityMapping(entityClass = WebPageComponentEntity.class)
public class WebPageComponent extends BaseConstantModel {

    private WebPageComponentType webPageComponentType;

    public WebPageComponent(BaseConstantEntity baseConstantEntity) {
        super(baseConstantEntity);
    }

    public WebPageComponentType getWebPageComponentType() {
        return webPageComponentType;
    }

    public void setWebPageComponentType(WebPageComponentType webPageComponentType) {
        this.webPageComponentType = webPageComponentType;
    }
}
