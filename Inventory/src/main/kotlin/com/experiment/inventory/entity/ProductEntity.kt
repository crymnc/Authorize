package com.experiment.inventory.entity

import com.experiment.inventory.entity.base.BaseAuditEntity
import javax.persistence.*

@Entity(name = "product")
class ProductEntity:BaseAuditEntity() {

    @Column(name = "name")
    var name:String? = null

    @ManyToOne
    @JoinColumn(name = "product_type_id", referencedColumnName = "id")
    var productType:ProductTypeEntity? = null

    @Column(name = "description")
    var description:String? = null

    @Column(name = "product_code")
    var productCode:String? = null

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var productPriceHistory: MutableList<ProductPriceHistoryEntity>? = null

}