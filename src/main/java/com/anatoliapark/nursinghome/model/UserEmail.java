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
    private Long user_id;

    @Column(name="email")
    private String email;
}
