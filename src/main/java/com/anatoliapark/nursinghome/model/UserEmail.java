package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.model.base.BaseEntityAudit;
import com.anatoliapark.nursinghome.model.base.User;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name="user_email")
public class UserEmail extends BaseEntityAudit {

    @Column(name="user_id")
    @NotEmpty
    private Long userId;

    @Column(name="email")
    @NotEmpty(message = "{user.email.NotEmpty}")
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
