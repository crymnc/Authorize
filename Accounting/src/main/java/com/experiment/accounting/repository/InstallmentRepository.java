package com.experiment.accounting.repository;

import com.experiment.accounting.entity.InstallmentEntity;
import com.experiment.accounting.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentRepository extends EntityRepository<InstallmentEntity> {
}
