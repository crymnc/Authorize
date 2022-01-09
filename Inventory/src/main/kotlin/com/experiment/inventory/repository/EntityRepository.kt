package com.experiment.inventory.repository

import com.experiment.inventory.entity.base.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface EntityRepository<T: BaseEntity>: JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}