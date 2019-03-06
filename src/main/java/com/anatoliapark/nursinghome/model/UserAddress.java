package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.model.base.BaseEntityAudit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_address")
public class UserAddress extends BaseEntityAudit {

    @Column(name="user_id")
    private Long userId;

    @Column(name="address")
    private String address;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
