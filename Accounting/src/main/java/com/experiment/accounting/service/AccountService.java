package com.experiment.accounting.service;

import com.experiment.accounting.entity.AccountEntity;
import com.experiment.accounting.repository.AccountRepository;
import com.experiment.accounting.repository.base.EntityRepository;
import com.experiment.accounting.service.base.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends EntityService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository, EntityRepository<AccountEntity> entityRepository) {
        super(entityRepository);
        this.accountRepository = accountRepository;
    }

    public AccountEntity findAccountByUserId(Long userId) {
        return accountRepository.findByUserId(userId);
    }
}
