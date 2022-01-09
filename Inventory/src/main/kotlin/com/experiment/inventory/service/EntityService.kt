package com.experiment.inventory.service

import com.experiment.inventory.entity.base.BaseEntity
import com.experiment.inventory.repository.EntityRepository
import com.experiment.inventory.service.base.BaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Service
import java.util.*

@Service
class EntityService<X:BaseEntity> @Autowired constructor(private var entityRepository: EntityRepository<X>): BaseService() {

    fun <T:X>save(baseEntity: T?): T? {
        if(baseEntity == null)
            return null
        return entityRepository.save(baseEntity)
    }

    fun <T:X> find(id: Long?, c: Class<T>): Optional<T> {
        if(id == null)
            return Optional.empty()
        val matcher = ExampleMatcher.matchingAll()
        val instance: T = createInstance(c)
        instance.id=id
        return entityRepository.findOne(Example.of(instance, matcher))
    }

    fun <T:X> findAll(c: Class<T>): List<T>? {
        val instance: T = createInstance(c)
        return entityRepository.findAll(Example.of(instance))
    }

    fun <T:X> delete(id: Long?, c: Class<T>) {
        val instance: T = createInstance(c)
        instance.id = id
        entityRepository.delete(instance)
    }
}