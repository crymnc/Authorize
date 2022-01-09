package com.experiment.inventory.entity

import com.experiment.inventory.entity.base.BaseEntity
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name="product_pcs")
class ProductPcsEntity:BaseEntity() {

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    var product:ProductEntity? = null

    @ManyToOne
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    var inventory: InventoryEntity? = null

    @Column(name="totalPcs")
    var totalPcs:BigDecimal? = null

    @Column(name="leftPcs")
    var leftPcs:BigDecimal? = null

    @ManyToOne
    @JoinColumn(name = "pcs_type", referencedColumnName = "id")
    var pcsType:PcsTypeEntity? = null
}