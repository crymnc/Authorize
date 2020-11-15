package com.anatoliapark.nursinghome.entity.base;

import com.anatoliapark.nursinghome.model.base.BaseConstantModel;
import com.anatoliapark.nursinghome.model.base.BaseModel;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseConstantEntity extends BaseEntityAudit{

    @Column(name="name")
    @NotEmpty(message = "{constantEntity.name.NotEmpty}")
    @Length(max = 30,message = "{constantEntity.name.Length}")
    protected String name;

    @Column(name = "dsc")
    @Length(max = 255, message = "{constantEntity.desc.Length}")
    protected String dsc;

    public BaseConstantEntity(BaseModel model) {
        super(model);
        BaseConstantModel baseConstantModel = (BaseConstantModel) model;
        this.setName(baseConstantModel.getName());
        this.setDsc(baseConstantModel.getDescription());
    }

    public BaseConstantEntity(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }
}
