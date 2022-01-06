package com.experiment.accounting.repository;

import com.experiment.accounting.entity.AccountEntity;
import com.experiment.accounting.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends EntityRepository<AccountEntity> {

    public AccountEntity findByUserId(Long userId);

}
