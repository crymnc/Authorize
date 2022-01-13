package com.experiment.inventory.repository

import com.experiment.inventory.entity.ProductPcsEntity
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductPcsRepository:EntityRepository<ProductPcsEntity> {

    fun findByInventoryIdAndProductId(inventoryId:Long, productId:Long):Optional<ProductPcsEntity>

}