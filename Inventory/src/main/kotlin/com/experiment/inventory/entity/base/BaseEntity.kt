package com.experiment.inventory.entity.base

import java.io.Serializable
import javax.persistence.*

@MappedSuperclass
open class BaseEntity:Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id", nullable = false, updatable = false)
    var id: Long? = null
}