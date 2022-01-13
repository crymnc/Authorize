package com.experiment.inventory.controller

import com.experiment.inventory.domain.Product
import com.experiment.inventory.entity.ProductEntity
import com.experiment.inventory.entity.base.BaseAuditEntity
import com.experiment.inventory.exception.BusinessException
import com.experiment.inventory.mapper.ProductMapper
import com.experiment.inventory.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import kotlin.reflect.KClass

@RestController
@RequestMapping("/api/products")
class ProductController<T> @Autowired constructor(val productService:ProductService<T>,val productMapper:ProductMapper) where T:BaseAuditEntity {

    @PostMapping
    fun createProduct(@Valid @RequestBody product:Product):ResponseEntity<Any>{
        if(product.id != null)
            throw BusinessException("Product id should be empty when creating")
        val productEntity = productMapper.toEntity(product) ?: throw BusinessException("Product cannot be null")
        productService.save(productEntity as T)
        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED")
    }

    @PutMapping
    fun updateProduct(@Valid @RequestBody product:Product):ResponseEntity<Any>{
        val existingProductEntity = productService.find(product.id ?: throw BusinessException("Product id should not be empty when updating"),ProductEntity::class as KClass<T>)
            .orElseThrow{BusinessException("Product cannot be found")}
        productMapper.updateEntity(product, existingProductEntity as ProductEntity)
        productService.save(existingProductEntity as T)
        return ResponseEntity.status(HttpStatus.OK).body("UPDATED")
    }

    @GetMapping("/{product-id}")
    fun getProductById(@PathVariable(name="product-id") productId:Long):ResponseEntity<Any>{
        val existingProductEntity = productService.find(productId,ProductEntity::class as KClass<T>)
            .orElseThrow{BusinessException("Product cannot be found")}
        return ResponseEntity.status(HttpStatus.OK).body(productMapper.toModel(existingProductEntity as ProductEntity))
    }

    @GetMapping("/{product-code}")
    fun getProductByCode(@PathVariable(name="product-code") productCode:String):ResponseEntity<Any>{
        val existingProductEntity = productService.findByProductCode(productCode)
            .orElseThrow{BusinessException("Product cannot be found")}
        return ResponseEntity.status(HttpStatus.OK).body(productMapper.toModel(existingProductEntity as ProductEntity))
    }

    @GetMapping
    fun listAllProducts():ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.OK).body(productMapper.toModelList(productService.findAll(ProductEntity::class as KClass<T>)as List<ProductEntity>) )
    }
}