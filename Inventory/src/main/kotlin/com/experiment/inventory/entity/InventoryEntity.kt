package com.experiment.inventory.entity

import com.experiment.inventory.entity.base.BaseAuditEntity
import javax.persistence.*

@Entity(name="inventory")
class InventoryEntity:BaseAuditEntity() {

    @Column(name="name")
    var name:String? = null

    @Column(name="description")
    var description:String? = null

    @ManyToMany(cascade = [CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE], fetch = FetchType.EAGER)
    @JoinTable(name = "inventory_product", joinColumns = [JoinColumn(name = "inventory_id", referencedColumnName = "id")], inverseJoinColumns = [JoinColumn(name = "product_id", referencedColumnName = "id")])
    var productEntities: MutableList<ProductEntity>? = null

}