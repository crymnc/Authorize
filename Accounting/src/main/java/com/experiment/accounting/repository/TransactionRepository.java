package com.experiment.accounting.repository;

import com.experiment.accounting.entity.TransactionEntity;
import com.experiment.accounting.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends EntityRepository<TransactionEntity> {
}
