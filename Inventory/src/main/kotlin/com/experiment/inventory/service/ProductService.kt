package com.experiment.inventory.service

import com.experiment.inventory.entity.ProductEntity
import com.experiment.inventory.entity.base.BaseAuditEntity
import com.experiment.inventory.entity.base.BaseEntity
import com.experiment.inventory.repository.ProductRepository
import com.experiment.inventory.service.base.EntityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService<T>
@Autowired constructor(
    private var productRepository: ProductRepository,
    private var cacheManager: CacheManager
) : EntityService<T>() where T : BaseEntity {

    fun findByProductCode(productCode: String): Optional<ProductEntity> {
        return productRepository.findByProductCode(productCode)
    }

    override fun save(baseEntity: T): T? {
        val productEntity:ProductEntity = baseEntity as ProductEntity
        productEntity.productPriceHistory?.firstOrNull{productPriceHistoryEntity -> productPriceHistoryEntity.id != null && productPriceHistoryEntity.discontinueDate == null}?.discontinueDate = Date()
        return super.save(baseEntity)
    }

}