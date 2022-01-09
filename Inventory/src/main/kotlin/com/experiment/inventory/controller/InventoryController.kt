package com.experiment.inventory.controller

import com.experiment.inventory.domain.Inventory
import com.experiment.inventory.entity.InventoryEntity
import com.experiment.inventory.entity.base.BaseEntity
import com.experiment.inventory.exception.BusinessException
import com.experiment.inventory.mapper.InventoryMapper
import com.experiment.inventory.service.EntityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("api/inventory")
class InventoryController @Autowired constructor(var entityService: EntityService<BaseEntity>, var inventoryMapper: InventoryMapper){

    @PostMapping
    fun createInventory(@RequestBody inventory: Inventory):ResponseEntity<Any>{
        if(inventory.id != null)
            throw BusinessException("Inventory id should be empty when creating new one");
        val inventoryEntity: InventoryEntity? = inventoryMapper.toEntity(inventory)
        entityService.save(inventoryEntity)
        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED")
    }

    @GetMapping("/{inventory-id}")
    fun getInventory(@PathVariable(name="inventory-id") inventoryId:Long):ResponseEntity<Any>{
        val inventoryEntity = entityService.find(inventoryId,InventoryEntity::class.java).orElseThrow { BusinessException("Inventory is not found with id") }
        return ResponseEntity.status(HttpStatus.OK).body(inventoryMapper.toModel(inventoryEntity))
    }
}