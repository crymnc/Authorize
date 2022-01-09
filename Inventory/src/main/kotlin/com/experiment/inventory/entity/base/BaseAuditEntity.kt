package com.experiment.inventory.entity.base

import com.experiment.inventory.utils.AuthUtils
import java.util.*
import javax.persistence.*

@MappedSuperclass
abstract class BaseAuditEntity:BaseEntity() {

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    var createdAt: Date? = null

    @Column(name = "created_by", nullable = false, updatable = false)
    var createdBy: Long? = null

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    var updatedAt: Date? = null

    @Column(name = "updated_by", nullable = false)
    var updatedBy: Long? = null

    @Column(name = "discontinue_date")
    @Temporal(TemporalType.TIMESTAMP)
    var discontinueDate: Date? = null

    @PrePersist
    fun setCreationParameters() {
        val userId: Long? = AuthUtils.getAuthenticatedUser().id
        val date:Date = Date()
        createdBy = userId
        createdAt = date
        updatedBy = userId
        updatedAt = date
    }

    @PreUpdate
    fun setUpdateParameters() {
        val userId: Long? = AuthUtils.getAuthenticatedUser().id
        updatedBy = userId
        updatedAt = Date()
    }
}