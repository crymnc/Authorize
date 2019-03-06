package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.model.base.BaseEntityAudit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="g_health_situation_type")
public class GuestHealthSituationType extends BaseEntityAudit {

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

}
