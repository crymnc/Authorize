package com.experiment.inventory.domain

import com.experiment.inventory.domain.base.BaseModel
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class Product :BaseModel(){
    @NotBlank(message = "{product.name.NotBlank}")
    var name:String? = null
    var description:String? = null
    @NotNull(message = "{product.productType.NotBlank}")
    var productType:Long? = null
    @NotBlank(message = "{product.productCode.NotBlank}")
    var productCode:String? = null
    @NotNull(message = "{product.price.NotNull}")
    var price:BigDecimal? = null
}