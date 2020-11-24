package com.anatoliapark.nursinghome.model.base;

import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;

public class BaseConstantModel extends BaseModel {

    private String name, description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BaseConstantModel(BaseConstantEntity baseConstantEntity) {
        super(baseConstantEntity);
        this.setDescription(baseConstantEntity.getDsc());
        this.setName(baseConstantEntity.getName());
    }

    public BaseConstantModel(){super();}
}
