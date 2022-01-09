package com.experiment.inventory.entity.base

import org.hibernate.validator.constraints.Length
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.validation.constraints.NotEmpty

@MappedSuperclass
open class BaseConstantEntity:BaseAuditEntity() {

    @Column(name = "name")
    @NotEmpty(message = "{constantEntity.name.NotEmpty}")
    @Length(max = 30, message = "{constantEntity.name.Length}")
    var name: String? = null

    @Column(name = "dsc")
    @Length(max = 255, message = "{constantEntity.desc.Length}")
    var dsc: String? = null

}