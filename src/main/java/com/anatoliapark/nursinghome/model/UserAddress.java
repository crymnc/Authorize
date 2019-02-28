package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.model.base.BaseEntityAudit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_address")
public class UserAddress extends BaseEntityAudit {

    @Column(name="user_id")
    private Long user_id;

    @Column(name="address")
    private String address;
}
