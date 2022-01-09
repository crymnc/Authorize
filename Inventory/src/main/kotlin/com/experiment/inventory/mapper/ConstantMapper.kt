package com.experiment.inventory.mapper

import com.experiment.inventory.domain.base.BaseConstantModel
import com.experiment.inventory.entity.base.BaseConstantEntity

class ConstantMapper {

    fun <X : BaseConstantModel?, Y : BaseConstantEntity?> toModel(constantEntity: Y): X? {
        if( constantEntity == null)
            return null
        val model = BaseConstantModel()
        model.id = constantEntity.id
        model.dsc = constantEntity.dsc
        model.name = constantEntity.name
        model.discontinueDate = constantEntity.discontinueDate
        model.createdAt = constantEntity.createdAt
        model.createdBy = constantEntity.createdBy
        model.updatedAt = constantEntity.updatedAt
        model.updatedBy = constantEntity.updatedBy

        return model as X
    }

    fun <X : BaseConstantEntity?, Y : BaseConstantModel?> toEntity(constantModel: Y, clazz: Class<X>): X? {
        val entity: X = clazz.getDeclaredConstructor().newInstance()
        if(constantModel == null || entity == null)
            return null

        entity.id = constantModel.id
        entity.name = constantModel.name
        entity.dsc = constantModel.dsc
        entity.discontinueDate = constantModel.discontinueDate

        return entity
    }
}