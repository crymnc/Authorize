package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.model.base.BaseEntityAudit;
import com.anatoliapark.nursinghome.model.base.User;

import javax.persistence.*;
import java.lang.reflect.Constructor;

@Entity
@Table(name="VALUE")
public class Value extends BaseEntityAudit {

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "variable_id", referencedColumnName = "id")
    private Variable variable;

    @Column(name="value")
    private String value;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public Object getValue() {
        try{
            Class<?> clazz = Class.forName(variable.getType().getName());
            Constructor<?> constructor=clazz.getConstructor(String.class);
            return constructor.newInstance(value);
        }
        catch (Exception e){
            System.out.println();
        }
        return null;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
