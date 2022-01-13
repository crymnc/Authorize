package com.experiment.inventory.domain

import java.math.BigDecimal
import javax.validation.constraints.NotNull

class PcsCreation {
    var id: Long? = null
    @NotNull(message = "{pcs.productId.NotNull}")
    var productId:Long? = null
    @NotNull(message = "{pcs.inventoryId.NotNull}")
    var inventoryId:Long? = null
    @NotNull(message = "{pcs.totalPcs.NotNull}")
    var totalPcs:BigDecimal? = null
    @NotNull(message = "{pcs.pcsType.NotNull}")
    var pcsType: Long? = null
}