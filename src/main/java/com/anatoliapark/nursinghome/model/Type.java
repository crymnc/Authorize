package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.model.base.BaseEntityAudit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TYPE")
public class Type extends BaseEntityAudit {

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() throws ClassNotFoundException{
        return Class.forName(type);
    }

    public void setType(Class clazz) {
        this.type = clazz.getName();
    }
}
