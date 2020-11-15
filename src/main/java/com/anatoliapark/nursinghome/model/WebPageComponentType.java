package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.annotation.EntityMapping;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.entity.webpage.WebPageComponentTypeEntity;
import com.anatoliapark.nursinghome.model.base.BaseConstantModel;

@EntityMapping(entityClass = WebPageComponentTypeEntity.class)
public class WebPageComponentType extends BaseConstantModel {
    public WebPageComponentType(BaseConstantEntity baseConstantEntity) {
        super(baseConstantEntity);
    }
}
