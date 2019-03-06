package com.anatoliapark.nursinghome.model;

import com.anatoliapark.nursinghome.model.base.BaseEntityAudit;

import javax.persistence.*;

@Entity
@Table(name = "g_health_situation")
public class GuestHealthSituation extends BaseEntityAudit {

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "situation_id", referencedColumnName = "id")
    private GuestHealthSituationType situationType;

}
