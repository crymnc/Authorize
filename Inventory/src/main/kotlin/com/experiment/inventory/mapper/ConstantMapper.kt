package com.experiment.inventory.mapper

import com.experiment.inventory.domain.base.BaseConstantModel
import com.experiment.inventory.entity.base.BaseConstantEntity
import org.springframework.stereotype.Component
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

@Component
class ConstantMapper {

    fun < Y> toModel(constantEntity: Y): BaseConstantModel where Y:BaseConstantEntity{
        val model = BaseConstantModel()
        model.id = constantEntity.id
        model.dsc = constantEntity.dsc
        model.name = constantEntity.name
        model.discontinueDate = constantEntity.discontinueDate
        model.createdAt = constantEntity.createdAt
        model.createdBy = constantEntity.createdBy
        model.updatedAt = constantEntity.updatedAt
        model.updatedBy = constantEntity.updatedBy

        return model
    }

    fun <X : BaseConstantEntity, Y : BaseConstantModel> toEntity(constantModel: Y, clazz: KClass<X>): X {
        val entity: X = clazz.createInstance()

        entity.id = constantModel.id
        entity.name = constantModel.name
        entity.dsc = constantModel.dsc
        entity.discontinueDate = constantModel.discontinueDate

        return entity
    }
}