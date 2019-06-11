package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.model.base.BaseEntityAudit;
import com.anatoliapark.nursinghome.model.base.User;

import javax.persistence.*;
import java.lang.reflect.Constructor;

@Entity
@Table(name = "VALUE")
public class Value<T> extends BaseEntityAudit {

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private Type type;

    @Column(name = "value")
    private String value;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public T getValue() {
        try {
            Class<?> clazz = type.getType();
            Constructor<?> constructor = clazz.getConstructor(String.class);
            return (T) constructor.newInstance(value);
        } catch (Exception e) {
            System.out.println();
        }
        return null;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
