package com.anatoliapark.nursinghome.entity.webpage;

import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.model.WebPageComponentType;
import com.anatoliapark.nursinghome.model.base.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "webpage_component_type")
@ModelMapping(modelClass = WebPageComponentType.class)
public class WebPageComponentTypeEntity extends BaseConstantEntity {

    public WebPageComponentTypeEntity(BaseModel model) {
        super(model);
    }

    public WebPageComponentTypeEntity(){}
}
