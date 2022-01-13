package com.experiment.inventory.domain.base

import javax.validation.constraints.NotBlank

open class BaseConstantModel:BaseModel() {
    @NotBlank(message = "{constant.name.NotBlank}")
    var name: String? = null
    var dsc: String? = null
}