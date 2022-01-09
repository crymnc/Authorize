package com.experiment.inventory.mapper

import com.experiment.inventory.domain.Inventory
import com.experiment.inventory.entity.InventoryEntity
import org.springframework.stereotype.Component

@Component
class InventoryMapper {

    fun toEntity(inventory: Inventory?):InventoryEntity?{
        if(inventory == null)
            return null
        val inventoryEntity:InventoryEntity = InventoryEntity()
        inventoryEntity.id = inventory.id
        inventoryEntity.name = inventory.name
        inventoryEntity.description = inventory.description
        inventoryEntity.discontinueDate = inventory.discontinueDate
        return inventoryEntity;
    }

    fun toModel(inventoryEntity:InventoryEntity):Inventory?{
        if(inventoryEntity == null)
            return null
        val inventory:Inventory = Inventory()
        inventory.id = inventoryEntity.id
        inventory.name = inventoryEntity.name
        inventory.description = inventoryEntity.description
        inventory.discontinueDate = inventoryEntity.discontinueDate
        inventory.createdAt = inventoryEntity.createdAt
        inventory.createdBy = inventoryEntity.createdBy
        inventory.updatedAt = inventoryEntity.updatedAt
        inventory.updatedBy = inventoryEntity.updatedBy
        return inventory;
    }
}