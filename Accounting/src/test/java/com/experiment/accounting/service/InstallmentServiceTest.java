package com.experiment.accounting.service;

import com.experiment.accounting.entity.InstallmentEntity;
import com.experiment.accounting.entity.InstallmentGroupEntity;
import com.experiment.accounting.repository.base.EntityRepository;
import com.experiment.accounting.utils.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class InstallmentServiceTest {

    @InjectMocks
    private InstallmentService installmentService;

    @Mock
    private EntityRepository entityRepository;

    public void setUp(){
        installmentService = new InstallmentService(entityRepository);
    }

    @Test
    public void shouldInstallmentsCountEqualToInstallmentNumber(){
        InstallmentGroupEntity installmentGroupEntity = createTestData();
        installmentService.createInstallments(installmentGroupEntity);
        Assertions.assertNotNull(installmentGroupEntity.getInstallments());
        Assertions.assertEquals(installmentGroupEntity.getInstallments().size(),installmentGroupEntity.getNumberOfInstallment());
    }

    @Test
    public void shouldInstallmentAmountEqualTotalAmountDividedByInstNumber(){
        InstallmentGroupEntity installmentGroupEntity = createTestData();
        installmentService.createInstallments(installmentGroupEntity);
        BigDecimal installmentAmount = installmentGroupEntity.getTotalAmount().divide(new BigDecimal(installmentGroupEntity.getNumberOfInstallment()),2, RoundingMode.FLOOR);
        installmentGroupEntity.getInstallments().forEach(installmentEntity -> {
            if(installmentEntity.getInstallmentNumber() !=1)
                Assertions.assertEquals(installmentAmount,installmentEntity.getAmount());
            else
                Assertions.assertEquals(new BigDecimal("166.70"),installmentEntity.getAmount());
        });
    }

    @Test
    public void shouldDueDateIncreaseMonthly(){
        InstallmentGroupEntity installmentGroupEntity = createTestData();
        installmentService.createInstallments(installmentGroupEntity);
        List<InstallmentEntity> installmentEntityList = new ArrayList<>(installmentGroupEntity.getInstallments());
        installmentEntityList.sort(Comparator.comparing(InstallmentEntity::getInstallmentNumber));
        for(int i = 0; i<installmentEntityList.size(); i++){
            Date updatedDate = DateUtils.getDateWithZeroTime(java.sql.Date.valueOf(LocalDate.now().plusMonths(i)));
            Assertions.assertEquals(updatedDate,installmentEntityList.get(i).getDueDate());
        }
    }

    @Test
    public void shouldInstallmentNumberIncreaseByOne(){
        InstallmentGroupEntity installmentGroupEntity = createTestData();
        installmentService.createInstallments(installmentGroupEntity);
        List<InstallmentEntity> installmentEntityList = new ArrayList<>(installmentGroupEntity.getInstallments());
        installmentEntityList.sort(Comparator.comparing(InstallmentEntity::getInstallmentNumber));
        for(int i = 0; i<installmentEntityList.size(); i++){
            Assertions.assertEquals(i+1,installmentEntityList.get(i).getInstallmentNumber());
        }
    }

    private InstallmentGroupEntity createTestData(){
        InstallmentGroupEntity installmentGroupEntity = new InstallmentGroupEntity();
        installmentGroupEntity.setNumberOfInstallment(6);
        installmentGroupEntity.setDescription("test");
        installmentGroupEntity.setTotalAmount(BigDecimal.valueOf(1000));
        return installmentGroupEntity;
    }


}
