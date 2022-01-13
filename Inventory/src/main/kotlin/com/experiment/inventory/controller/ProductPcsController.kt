package com.experiment.inventory.controller

import com.experiment.inventory.domain.PcsCreation
import com.experiment.inventory.entity.ProductPcsEntity
import com.experiment.inventory.entity.base.BaseEntity
import com.experiment.inventory.exception.BusinessException
import com.experiment.inventory.mapper.PcsMapper
import com.experiment.inventory.service.ProductPcsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import javax.validation.Valid
import kotlin.reflect.KClass

@RestController
@RequestMapping("/api/product-pcs")
class ProductPcsController<T> @Autowired
    constructor(
    private var pcsMapper: PcsMapper,
    private var productPcsService: ProductPcsService<T>) where T:BaseEntity {

    @PostMapping
    fun createPcs(@Valid @RequestBody productPcs:PcsCreation):ResponseEntity<Any>{
        if(productPcs.id != null)
            throw BusinessException("Pcs id should be empty when creating")
        val productPcsEntity = pcsMapper.toEntity(productPcs)
        productPcsService.save(productPcsEntity as T)
        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED")
    }

    @PutMapping
    fun updatePcs(@Valid @RequestBody productPcs:PcsCreation):ResponseEntity<Any>{
        if(productPcs.id == null)
            throw BusinessException("Pcs id should not be empty when updating")
        val existingPcs = productPcsService.find(productPcs.id!!, ProductPcsEntity::class as KClass<T>).orElseThrow{BusinessException("Product pcs is not found by id")}
        pcsMapper.updateEntity(productPcs,existingPcs as ProductPcsEntity)
        productPcsService.save(existingPcs as T)
        return ResponseEntity.status(HttpStatus.OK).body("UPDATED")
    }

    @PutMapping("/inventory/{inventory-id}/product/{product-id}/pcs-amount/{pcs-amount}")
    fun addPcsToProduct(@PathVariable(name="inventory-id") inventoryId:Long,
                        @PathVariable(name="product-id") productId:Long,
                        @PathVariable(name="pcs-amount") pcsAmount:BigDecimal):ResponseEntity<Any>{
        val productPcsEntity = productPcsService.findByInventoryIdAndProductId(inventoryId,productId).orElseThrow { BusinessException("Product pcs is not found by inventory id and product id") }
        productPcsService.increasePcsAmount(pcsAmount,productPcsEntity)
        productPcsService.save(productPcsEntity as T)
        return ResponseEntity.status(HttpStatus.OK).body("INCREASED")
    }

    @GetMapping("/inventory/{inventory-id}/product/{product-id}")
    fun getProductPcs(@PathVariable(name="inventory-id") inventoryId:Long,
                      @PathVariable(name="product-id") productId:Long):ResponseEntity<Any>{
        val productPcsEntity = productPcsService.findByInventoryIdAndProductId(inventoryId,productId).orElseThrow { BusinessException("Product pcs is not found by inventory id and product id") }
        return ResponseEntity.status(HttpStatus.OK).body(pcsMapper.toModel(productPcsEntity))
    }

    @GetMapping("/inventory/{inventory-id}/product/{product-id}/pcs-amount/{pcs-amount}")
    fun takePcsFromProduct(@PathVariable(name="inventory-id") inventoryId:Long,
                        @PathVariable(name="product-id") productId:Long,
                        @PathVariable(name="pcs-amount") pcsAmount:BigDecimal):ResponseEntity<Any>{
        val productPcsEntity = productPcsService.findByInventoryIdAndProductId(inventoryId,productId).orElseThrow { BusinessException("Product pcs is not found by inventory id and product id") }
        productPcsService.decreasePcsAmount(pcsAmount,productPcsEntity)
        productPcsService.save(productPcsEntity as T)
        return ResponseEntity.status(HttpStatus.OK).body("DECREASED")
    }
}