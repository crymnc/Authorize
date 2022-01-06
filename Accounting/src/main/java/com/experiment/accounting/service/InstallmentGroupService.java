package com.experiment.accounting.service;

import com.experiment.accounting.entity.InstallmentGroupEntity;
import com.experiment.accounting.repository.base.EntityRepository;
import com.experiment.accounting.service.base.EntityService;
import org.springframework.stereotype.Service;

@Service
public class InstallmentGroupService extends EntityService {

    public InstallmentGroupService(EntityRepository<InstallmentGroupEntity> entityRepository){
        super(entityRepository);
    }
}
