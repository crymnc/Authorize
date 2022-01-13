package com.experiment.inventory.service

import com.experiment.inventory.entity.ProductPcsEntity
import com.experiment.inventory.entity.base.BaseEntity
import com.experiment.inventory.exception.BusinessException
import com.experiment.inventory.repository.ProductPcsRepository
import com.experiment.inventory.service.base.EntityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class ProductPcsService<T> @Autowired constructor(private val productPcsRepository: ProductPcsRepository):EntityService<T>() where T: BaseEntity {

    fun findByInventoryIdAndProductId(inventoryId:Long, productId:Long): Optional<ProductPcsEntity> {
        return productPcsRepository.findByInventoryIdAndProductId(inventoryId,productId)
    }

    fun increasePcsAmount(pcsAmount:BigDecimal, pcsEntity: ProductPcsEntity){
        var newLeftPcs = pcsEntity.leftPcs!!.add(pcsAmount)
        if(newLeftPcs > pcsEntity.totalPcs)
            pcsEntity.totalPcs = newLeftPcs
        pcsEntity.leftPcs = newLeftPcs
    }

    fun decreasePcsAmount(pcsAmount:BigDecimal, pcsEntity: ProductPcsEntity){
        var newLeftPcs = pcsEntity.leftPcs!!.subtract(pcsAmount)
        if(newLeftPcs < BigDecimal.ZERO)
            throw BusinessException("Not enough product to take")
        pcsEntity.leftPcs = newLeftPcs
    }
}