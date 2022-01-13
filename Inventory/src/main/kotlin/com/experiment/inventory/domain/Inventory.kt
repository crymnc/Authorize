package com.experiment.inventory.domain

import com.experiment.inventory.domain.base.BaseModel
import javax.validation.constraints.NotBlank

class Inventory:BaseModel() {
    @NotBlank(message = "{inventory.name.NotBlank}")
    var name:String? = null
    var description:String? = null
}