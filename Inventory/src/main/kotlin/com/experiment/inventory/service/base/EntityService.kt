package com.experiment.inventory.service.base

import com.experiment.inventory.entity.base.BaseEntity
import com.experiment.inventory.repository.EntityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Service
import java.util.*
import kotlin.reflect.KClass

@Service
class EntityService<T>:BaseService() where T: BaseEntity {

    @Autowired
    private var entityRepository: EntityRepository<T>? = null

    fun save(baseEntity: T): T? {
        return entityRepository!!.save(baseEntity)
    }

    fun find(id: Long, c: KClass<T>): Optional<T> {
        val matcher = ExampleMatcher.matchingAll()
        val instance: T = createInstance(c)
        instance.id=id
        return entityRepository!!.findOne(Example.of(instance, matcher))
    }

    fun findAll(c: KClass<T>): List<T> {
        val instance: T = createInstance(c)
        return entityRepository!!.findAll(Example.of(instance))
    }

    fun delete(id: Long, c: KClass<T>) {
        val instance: T = createInstance(c)
        instance.id = id
        entityRepository!!.delete(instance)
    }
}