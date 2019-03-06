package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.model.base.BaseEntityAudit;
import com.fasterxml.jackson.databind.ser.Serializers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_email")
public class UserEmail extends BaseEntityAudit {

    @Column(name="user_id")
    private Long userId;

    @Column(name="email")
    private String email;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
