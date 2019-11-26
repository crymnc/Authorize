package com.anatoliapark.nursinghome.model.base;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseConstantEntity extends BaseEntityAudit{

    @Column(name="name")
    @NotEmpty(message = "{constantEntity.name.NotEmpty}")
    @Length(max = 30,message = "{constantEntity.name.Length}")
    private String name;

    @Column(name = "dsc")
    @Length(max = 255, message = "{constantEntity.desc.Length}")
    private String dsc;

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

    public Boolean isNew(){
        return getId() == null;
    }
}
