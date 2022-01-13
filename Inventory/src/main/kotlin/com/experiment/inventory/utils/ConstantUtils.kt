package com.experiment.inventory.utils

import com.experiment.inventory.entity.PcsTypeEntity
import com.experiment.inventory.entity.ProductTypeEntity
import com.experiment.inventory.entity.base.BaseConstantEntity
import com.experiment.inventory.exception.BusinessException

class ConstantUtils {

    companion object{
        private val constants: MutableList<ConstantReference<out BaseConstantEntity>> = ArrayList()
        private var productTypeReference = ConstantReference<ProductTypeEntity>()
        private var pcsTypeReference = ConstantReference<PcsTypeEntity>()

        init{
            productTypeReference.clazz = ProductTypeEntity::class
            productTypeReference.name = "product-type"
            productTypeReference.desc = "Product Type"

            pcsTypeReference.clazz = PcsTypeEntity::class
            pcsTypeReference.name = "pcs-type"
            pcsTypeReference.desc = "Pcs Type"

            constants.add(productTypeReference)
            constants.add(pcsTypeReference)
        }

        fun getReferenceByName(name: String?): ConstantReference<out BaseConstantEntity> {
            return constants.stream().filter{ constantHierarchy: ConstantReference<out BaseConstantEntity> ->
                constantHierarchy.name.equals(
                    name
                )
            }.findFirst().orElseThrow{ BusinessException("Constant name is not acceptable")}
        }
    }
}