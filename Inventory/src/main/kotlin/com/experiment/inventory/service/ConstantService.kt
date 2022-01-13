package com.experiment.inventory.service

import com.experiment.inventory.domain.base.BaseConstantModel
import com.experiment.inventory.entity.base.BaseConstantEntity
import com.experiment.inventory.exception.BusinessException
import com.experiment.inventory.mapper.ConstantMapper
import com.experiment.inventory.repository.EntityRepository
import com.experiment.inventory.service.base.EntityService
import com.experiment.inventory.utils.ConstantUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import java.util.*
import kotlin.reflect.KClass

@Service
class ConstantService<T>
@Autowired constructor(
    var entityRepository:EntityRepository<T>,
    var cacheManager: CacheManager,
    var constantMapper: ConstantMapper): EntityService<T>() where T:BaseConstantEntity {


    @Cacheable(value = ["findReference"], key = "#name + #c.getName()")
    fun find(name: String, c: KClass<T>): Optional<T> {
        val instance = createInstance(c)
        instance.name = name
        return entityRepository.findOne(Example.of(instance))
    }

    @Cacheable(value = ["findReferenceByName"], key = "#constantName")
    fun  findAllByConstantName(constantName: String): List<BaseConstantModel> {
        return findAll(ConstantUtils.getReferenceByName(constantName).clazz as KClass<T>)
            .map{ constantEntity -> constantMapper.toModel(constantEntity)}.toList()
    }

    fun deleteByConstantNameAndId(constantName: String, id: Long) {
        delete(id, ConstantUtils.getReferenceByName(constantName).clazz as KClass<T>)
        cacheManager.getCache("findReferenceByName")?.evictIfPresent(constantName)
    }

    fun saveByConstantName(constantName: String, constantModel: BaseConstantModel): T? {
        if (constantModel.id != null) throw BusinessException("ID is not null, please try to update constant ($constantName)")
        val entity: T? = save(constantMapper.toEntity(constantModel, ConstantUtils.getReferenceByName(constantName).clazz as KClass<T>))
        cacheManager.getCache("findReferenceByName")?.evictIfPresent(constantName)
        return entity
    }

    fun updateByConstantName(constantName: String, constantModel: BaseConstantModel): T? {
        if (constantModel.id == null) throw BusinessException("ID is null, try to create constant ($constantName)")
        val reference = ConstantUtils.getReferenceByName(constantName);
        val clazz = reference.clazz as KClass<T>
        val entity: T? = save(constantMapper.toEntity(constantModel, clazz))
        cacheManager.getCache("findReferenceByName")?.evictIfPresent(constantName)
        return entity
    }
}