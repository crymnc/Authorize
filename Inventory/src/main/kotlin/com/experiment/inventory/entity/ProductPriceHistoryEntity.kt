package com.experiment.inventory.entity

import com.experiment.inventory.entity.base.BaseAuditEntity
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name="product_price_history")
class ProductPriceHistoryEntity: BaseAuditEntity() {

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    var product:ProductEntity? = null

    @Column(name="price")
    var price:BigDecimal? = null

}