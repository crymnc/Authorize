package com.experiment.inventory.repository

import com.experiment.inventory.entity.ProductEntity
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository:EntityRepository<ProductEntity> {

    fun findByProductCode(productCode:String): Optional<ProductEntity>;

}