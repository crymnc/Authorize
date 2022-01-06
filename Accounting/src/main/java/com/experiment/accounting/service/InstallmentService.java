package com.experiment.accounting.service;

import com.experiment.accounting.entity.InstallmentEntity;
import com.experiment.accounting.entity.InstallmentGroupEntity;
import com.experiment.accounting.repository.base.EntityRepository;
import com.experiment.accounting.service.base.EntityService;
import com.experiment.accounting.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class InstallmentService extends EntityService {

    public InstallmentService(EntityRepository<InstallmentEntity> entityRepository){
        super(entityRepository);
    }

    public void createInstallments(InstallmentGroupEntity installmentGroupEntity) {
        BigDecimal installmentAmount = installmentGroupEntity.getTotalAmount().divide(new BigDecimal(installmentGroupEntity.getNumberOfInstallment()),2,RoundingMode.FLOOR);
        BigDecimal leftAmountFromRounding = installmentGroupEntity.getTotalAmount();
        LocalDate currentDate = LocalDate.now();
        for (int i = 0; i < installmentGroupEntity.getNumberOfInstallment(); i++) {
            InstallmentEntity installmentEntity = new InstallmentEntity();
            leftAmountFromRounding = leftAmountFromRounding.subtract(installmentAmount);
            installmentEntity.setAmount(installmentAmount);
            installmentEntity.setInstallmentNumber(i+1);
            LocalDate updatedDate = currentDate.plusMonths(i);
            installmentEntity.setDueDate(DateUtils.getDateWithZeroTime(java.sql.Date.valueOf(updatedDate)));
            installmentGroupEntity.addInstallment(installmentEntity);
            installmentEntity.setInstallmentGroup(installmentGroupEntity);
        }
        addRemainingAmountToFirstInstallment(installmentGroupEntity,leftAmountFromRounding);
    }

    private void addRemainingAmountToFirstInstallment(InstallmentGroupEntity installmentGroupEntity, BigDecimal roundingAmount){
        List<InstallmentEntity> installmentEntities = new ArrayList<>(installmentGroupEntity.getInstallments());
        installmentEntities.sort(Comparator.comparing(InstallmentEntity::getInstallmentNumber));
        installmentEntities.get(0).setAmount(installmentEntities.get(0).getAmount().add(roundingAmount));
    }
}
