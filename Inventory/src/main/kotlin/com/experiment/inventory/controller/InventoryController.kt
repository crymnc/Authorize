package com.experiment.inventory.controller

import com.experiment.inventory.domain.Inventory
import com.experiment.inventory.entity.InventoryEntity
import com.experiment.inventory.entity.ProductEntity
import com.experiment.inventory.entity.base.BaseAuditEntity
import com.experiment.inventory.exception.BusinessException
import com.experiment.inventory.mapper.InventoryMapper
import com.experiment.inventory.mapper.ProductMapper
import com.experiment.inventory.service.ProductService
import com.experiment.inventory.service.base.EntityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import kotlin.reflect.KClass

@RestController
@RequestMapping("/api/inventories")
class InventoryController<T>
@Autowired constructor(
    var entityService: EntityService<T>,
    var productService: ProductService<T>,
    var inventoryMapper: InventoryMapper,
    var productMapper: ProductMapper) where T:BaseAuditEntity{

    @PostMapping
    fun createInventory(@Valid @RequestBody inventory: Inventory):ResponseEntity<Any>{
        if(inventory.id != null)
            throw BusinessException("Inventory id should be empty when creating new one")
        val inventoryEntity: InventoryEntity = inventoryMapper.toEntity(inventory) ?: throw BusinessException("Inventory is null")
        entityService.save(inventoryEntity as T)
        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED")
    }

    @PutMapping
    fun updateInventory(@Valid @RequestBody inventory: Inventory):ResponseEntity<Any>{
        if(inventory.id == null)
            throw BusinessException("Inventory id should not be empty when updating")
        val inventoryEntity: InventoryEntity = entityService.find(inventory.id!!, InventoryEntity::class as KClass<T>).orElseThrow{BusinessException("Inventory cannot be found")} as InventoryEntity
        inventoryMapper.updateEntity(inventory,inventoryEntity)
        entityService.save(inventoryEntity as T)
        return ResponseEntity.status(HttpStatus.OK).body("UPDATED")
    }

    @GetMapping
    fun listInventories():ResponseEntity<Any>{
        val inventoryEntities = entityService.findAll(InventoryEntity::class as KClass<T>) as List<InventoryEntity>
        return ResponseEntity.status(HttpStatus.OK).body(inventoryMapper.toModelList(inventoryEntities))
    }

    @GetMapping("/{inventory-id}")
    fun getInventory(@PathVariable(name="inventory-id") inventoryId:Long):ResponseEntity<Any>{
        val inventoryEntity = entityService.find(inventoryId,InventoryEntity::class as KClass<T>).orElseThrow { BusinessException("Inventory is not found with id") } as InventoryEntity
        return ResponseEntity.status(HttpStatus.OK).body(inventoryMapper.toModel(inventoryEntity))
    }

    @PutMapping("/{inventory-id}/products/id/{product-id}")
    fun addProductToInventory(@PathVariable(name="inventory-id") inventoryId:Long, @PathVariable(name="product-id") productId:Long):ResponseEntity<Any>{
        val productEntity = entityService.find(productId,ProductEntity::class as KClass<T>).orElseThrow { BusinessException("Product not found by id") } as ProductEntity
        val inventoryEntity = entityService.find(inventoryId, InventoryEntity::class as KClass<T>).orElseThrow{ BusinessException("Inventory not found by id")} as InventoryEntity
        inventoryEntity.addProduct(productEntity)
        entityService.save(inventoryEntity as T)
        return ResponseEntity.status(HttpStatus.OK).body("ADDED")
    }

    @PutMapping("/{inventory-id}/products/code/{product-code}")
    fun addProductByCodeToInventory(@PathVariable(name="inventory-id") inventoryId:Long, @PathVariable(name="product-code") productCode:String):ResponseEntity<Any>{
        val productEntity = productService.findByProductCode(productCode).orElseThrow { BusinessException("Product not found by code") }
        val inventoryEntity = entityService.find(inventoryId, InventoryEntity::class as KClass<T>).orElseThrow{ BusinessException("Inventory not found by id")} as InventoryEntity
        inventoryEntity.addProduct(productEntity)
        entityService.save(inventoryEntity as T)
        return ResponseEntity.status(HttpStatus.OK).body("ADDED")
    }

    @GetMapping("{inventory-id}/products")
    fun listAllProductsByInventoryId(@PathVariable(name="inventory-id") inventoryId:Long):ResponseEntity<Any>{
        val inventoryEntity = entityService.find(inventoryId,InventoryEntity::class as KClass<T>).orElseThrow { BusinessException("Inventory is not found with id") } as InventoryEntity
        if(inventoryEntity.productEntities != null)
            return ResponseEntity.status(HttpStatus.OK).body(productMapper.toModelList(inventoryEntity.productEntities))
        return ResponseEntity.status(HttpStatus.OK).body(ArrayList<Any>())
    }


}