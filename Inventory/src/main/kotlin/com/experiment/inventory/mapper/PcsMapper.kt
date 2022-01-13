package com.experiment.inventory.mapper

import com.experiment.inventory.domain.Pcs
import com.experiment.inventory.domain.PcsCreation
import com.experiment.inventory.entity.InventoryEntity
import com.experiment.inventory.entity.PcsTypeEntity
import com.experiment.inventory.entity.ProductEntity
import com.experiment.inventory.entity.ProductPcsEntity
import org.springframework.stereotype.Component

@Component
class PcsMapper {

    fun toModel(pcsEntity: ProductPcsEntity):Pcs?{
        if(pcsEntity == null)
            return null
        val pcs = Pcs()
        pcs.id= pcsEntity.id
        pcs.productId = pcsEntity.product!!.id
        pcs.inventoryId = pcsEntity.inventory!!.id
        pcs.pcsType = pcsEntity.pcsType!!.id
        pcs.totalPcs = pcsEntity.totalPcs
        pcs.leftPcs = pcsEntity.leftPcs
        return pcs;
    }

    fun toEntity(pcs:PcsCreation):ProductPcsEntity?{
        if(pcs == null)
            return null
        val pcsEntity=ProductPcsEntity()

        val inventoryEntity = InventoryEntity()
        inventoryEntity.id = pcs.inventoryId
        val productEntity = ProductEntity()
        productEntity.id = pcs.productId
        pcsEntity.inventory = inventoryEntity
        pcsEntity.product = productEntity
        pcsEntity.totalPcs = pcs.totalPcs
        pcsEntity.leftPcs = pcs.totalPcs

        val pcsTypeEntity = PcsTypeEntity()
        pcsTypeEntity.id = pcs.pcsType
        pcsEntity.pcsType = pcsTypeEntity
        return pcsEntity
    }

    fun updateEntity(pcsCreation: PcsCreation, productPcsEntity: ProductPcsEntity){
        var pcsTypeEntity = PcsTypeEntity()
        pcsTypeEntity.id = pcsCreation.pcsType
        productPcsEntity.pcsType = pcsTypeEntity
        productPcsEntity.totalPcs = pcsCreation.totalPcs
        val inventoryEntity = InventoryEntity()
        inventoryEntity.id = pcsCreation.inventoryId
        val productEntity = ProductEntity()
        productPcsEntity.id = pcsCreation.productId
        productPcsEntity.inventory = inventoryEntity
        productPcsEntity.product = productEntity
    }
}