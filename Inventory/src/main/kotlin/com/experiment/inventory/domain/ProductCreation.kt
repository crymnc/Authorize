package com.experiment.inventory.domain

import com.experiment.inventory.domain.base.BaseModel

class ProductCreation:BaseModel() {
    var name:String? = null
    var inventoryId:Long? = null
    var description:String? = null
    var productType:Long? = null
    var productCode:String? = null
}