package com.experiment.inventory.mapper

import com.experiment.inventory.domain.Product
import com.experiment.inventory.entity.ProductEntity
import com.experiment.inventory.entity.ProductPriceHistoryEntity
import com.experiment.inventory.entity.ProductTypeEntity
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import kotlin.collections.ArrayList

@Component
class ProductMapper {

    fun toModel(productEntity: ProductEntity?): Product? {
        if (productEntity == null)
            return null
        val product = Product()
        product.productCode = productEntity.productCode
        product.productType = productEntity.productType?.id
        product.name = productEntity.name
        product.description = productEntity.description
        product.id = productEntity.id
        product.updatedBy = productEntity.updatedBy
        product.updatedAt = productEntity.updatedAt
        product.createdBy = productEntity.createdBy
        product.createdAt = productEntity.createdAt
        product.discontinueDate = productEntity.discontinueDate
        if (productEntity.productPriceHistory != null)
            product.price = productEntity.productPriceHistory?.firstOrNull { productPriceHistoryEntity -> productPriceHistoryEntity.discontinueDate == null }?.price?.setScale(2, RoundingMode.FLOOR)
        return product;
    }

    fun toEntity(product: Product?): ProductEntity? {
        if (product == null)
            return null
        val productEntity: ProductEntity = ProductEntity()
        val productTypeEntity: ProductTypeEntity = ProductTypeEntity()

        productTypeEntity.id = product.productType
        productEntity.productType = productTypeEntity
        productEntity.name = product.name
        productEntity.description = product.description
        productEntity.id = product.id
        productEntity.productCode = product.productCode
        productEntity.discontinueDate = product.discontinueDate

        val productPriceEntity = ProductPriceHistoryEntity()
        productPriceEntity.price = product.price!!.setScale(2, RoundingMode.FLOOR)
        productPriceEntity.product = productEntity

        productEntity.addProductPrice(productPriceEntity)
        return productEntity;
    }

    fun updateEntity(product: Product, productEntity: ProductEntity){
        productEntity.name = product.name
        productEntity.description = product.description
        productEntity.discontinueDate = product.discontinueDate
        productEntity.productCode = product.productCode

        val productType = ProductTypeEntity()
        productType.id = product.productType
        productEntity.productType = productType

        if (productEntity.productPriceHistory != null) {
            productEntity.productPriceHistory?.first { productPriceHistoryEntity -> productPriceHistoryEntity.discontinueDate == null }?.discontinueDate = Date()
        }

        val productPriceEntity = ProductPriceHistoryEntity()
        productPriceEntity.price = product.price!!.setScale(2, RoundingMode.FLOOR)
        productPriceEntity.product = productEntity
        productEntity.addProductPrice(productPriceEntity)
    }

    fun toModelList(productEntities: List<ProductEntity>?): List<Product?> {
        if (productEntities == null)
            return ArrayList()
        return productEntities.map { productEntity -> toModel(productEntity) }.toList()
    }
}