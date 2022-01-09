package com.experiment.inventory.domain.base

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.Column
import javax.persistence.Temporal
import javax.persistence.TemporalType

abstract class BaseModel {

    var id: Long? = null
    @JsonIgnore
    var createdBy: Long? = null
    @JsonIgnore
    var createdAt: Date? = null
    @JsonIgnore
    var updatedAt: Date? = null
    @JsonIgnore
    var updatedBy: Long? = null
    var discontinueDate: Date? = null
}