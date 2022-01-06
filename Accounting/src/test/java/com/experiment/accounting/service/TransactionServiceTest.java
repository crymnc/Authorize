package com.experiment.accounting.service;

import com.experiment.accounting.domain.TransactionCreation;
import com.experiment.accounting.entity.InstallmentEntity;
import com.experiment.accounting.entity.InstallmentGroupEntity;
import com.experiment.accounting.entity.TransactionEntity;
import com.experiment.accounting.exception.BusinessException;
import com.experiment.accounting.exception.BusinessExceptions;
import com.experiment.accounting.mapper.TransactionMapper;
import com.experiment.accounting.repository.base.EntityRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private EntityRepository entityRepository;

    @Mock
    private InstallmentGroupService installmentGroupService;

    private TransactionMapper transactionMapper;

    private InstallmentService installmentService;

    @BeforeEach
    public void setUp() {
        transactionMapper = new TransactionMapper();
        installmentService = new InstallmentService(entityRepository);
        transactionService = new TransactionService(entityRepository, transactionMapper, installmentGroupService);
    }

    @Test
    public void shouldTotalAmountAndTotalTransactionAmountIsEqualWhenCreateFullPaymentTransaction() {
        Optional<InstallmentGroupEntity> installmentGroupEntity = createInstallmentGroupEntity();
        Mockito.when(installmentGroupService.find(any(), eq(InstallmentGroupEntity.class))).thenReturn(installmentGroupEntity);
        Mockito.when(installmentGroupService.save(any())).thenReturn(null);
        transactionService.createTransaction(createTransactionCreation());

        installmentGroupEntity.get().getInstallments().forEach(installmentEntity -> {
            BigDecimal totalTransactionAmount = installmentEntity.getTransactions().stream()
                    .map(transactionEntity -> transactionEntity.getCancelDate() == null ? transactionEntity.getAmount() : BigDecimal.ZERO)
                    .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.FLOOR);
            Assertions.assertNotNull(totalTransactionAmount);
            Assertions.assertEquals(installmentEntity.getAmount(), totalTransactionAmount);
        });
    }

    @Test
    public void shouldAddAmountOnPartialPaidInstallment() {
        Optional<InstallmentGroupEntity> installmentGroupEntity = createInstallmentGroupEntity();
        addTransactionToFirstInstallment(installmentGroupEntity,BigDecimal.valueOf(50));
        TransactionCreation transactionCreation = createTransactionCreation();
        transactionCreation.setAmount(BigDecimal.valueOf(200));

        Mockito.when(installmentGroupService.find(any(), eq(InstallmentGroupEntity.class))).thenReturn(installmentGroupEntity);
        transactionService.createTransaction(transactionCreation);

        BigDecimal totalTransactionAmount = BigDecimal.ZERO;

        List<InstallmentEntity> savedInstallmentEntityList = new ArrayList<>(installmentGroupEntity.get().getInstallments());
        savedInstallmentEntityList.sort(Comparator.comparing(InstallmentEntity::getInstallmentNumber));
        for (int i = 0; i < savedInstallmentEntityList.size(); i++) {
            InstallmentEntity installmentEntity = savedInstallmentEntityList.get(i);
            if (installmentEntity.getTransactions() != null) {
                BigDecimal currentAmount = installmentEntity.getTransactions().stream()
                        .map(transactionEntity -> transactionEntity.getCancelDate() == null ? transactionEntity.getAmount() : BigDecimal.ZERO)
                        .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.FLOOR);
                totalTransactionAmount = totalTransactionAmount.add(currentAmount);

                if (i == 0) {
                    Assertions.assertNotNull(currentAmount);
                    Assertions.assertEquals(currentAmount, new BigDecimal("166.70"));
                } else if (i == 1) {
                    Assertions.assertNotNull(currentAmount);
                    Assertions.assertEquals(currentAmount, new BigDecimal("83.30"));
                }
            }
        }
        Assertions.assertEquals(totalTransactionAmount, new BigDecimal("250.00"));
    }

    @Test
    public void shouldLeftAmountOnGroupBeDecreasedWhenCreateRefundTransaction() {
        Optional<InstallmentGroupEntity> installmentGroupEntity = createInstallmentGroupEntity();
        addTransactionToFirstInstallment(installmentGroupEntity,BigDecimal.valueOf(150));
        Mockito.when(installmentGroupService.find(any(), eq(InstallmentGroupEntity.class))).thenReturn(installmentGroupEntity);
        Mockito.when(installmentGroupService.save(any())).thenReturn(null);

        TransactionCreation transactionCreation = createTransactionCreation();
        transactionCreation.setAmount(BigDecimal.valueOf(-47));
        transactionService.createTransaction(transactionCreation);

        BigDecimal amount = installmentGroupEntity.get().getInstallments()
                .stream()
                .filter(installmentEntity -> installmentEntity.getInstallmentNumber().equals(1))
                .map(installmentEntity -> installmentEntity
                        .getTransactions().stream().map(TransactionEntity::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2,RoundingMode.FLOOR);
        Assertions.assertEquals(new BigDecimal("103.00"), amount);

    }

    @Test
    public void shouldThrowErrorIfRefundTransactionAmountNotEqualTotalAmount() {
        Optional<InstallmentGroupEntity> installmentGroupEntity = createInstallmentGroupEntity();
        addTransactionToFirstInstallment(installmentGroupEntity,BigDecimal.valueOf(150));
        Mockito.when(installmentGroupService.find(any(), eq(InstallmentGroupEntity.class))).thenReturn(installmentGroupEntity);

        TransactionCreation transactionCreation = createTransactionCreation();
        transactionCreation.setAmount(BigDecimal.valueOf(-200));

        AssertionsForClassTypes.assertThatThrownBy(() -> transactionService.createTransaction(transactionCreation))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(BusinessExceptions.PAYMENT_AMOUNT_CANNOT_BE_MORE_OR_LESS_THAN_INST_GROUP_TOTAL_AMOUNT.getMessage());
    }

    @Test
    public void shouldThrowErrorIfInstGroupAmountNotEqualTransactionAmount() {
        Optional<InstallmentGroupEntity> installmentGroupEntity = createInstallmentGroupEntity();
        TransactionCreation transactionCreation = createTransactionCreation();
        transactionCreation.setAmount(BigDecimal.valueOf(1200));
        Mockito.when(installmentGroupService.find(any(), eq(InstallmentGroupEntity.class))).thenReturn(installmentGroupEntity);

        AssertionsForClassTypes.assertThatThrownBy(() -> transactionService.createTransaction(transactionCreation))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(BusinessExceptions.PAYMENT_AMOUNT_CANNOT_BE_MORE_OR_LESS_THAN_INST_GROUP_TOTAL_AMOUNT.getMessage());
    }

    @Test
    public void shouldThrowErrorIfInstallmentsEmptyOrNull() {
        Optional<InstallmentGroupEntity> installmentGroupEntity = createInstallmentGroupEntity();
        installmentGroupEntity.get().setInstallments(null);
        TransactionCreation transactionCreation = createTransactionCreation();
        Mockito.when(installmentGroupService.find(any(), eq(InstallmentGroupEntity.class))).thenReturn(installmentGroupEntity);

        AssertionsForClassTypes.assertThatThrownBy(() -> transactionService.createTransaction(transactionCreation))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(BusinessExceptions.INSTALLMENTS_SHOULD_BE_CREATED_FIRST.getMessage());
    }

    @Test
    public void shouldThrowErrorIfInstGroupIdNull() {
        Mockito.when(installmentGroupService.find(any(), eq(InstallmentGroupEntity.class))).thenReturn(Optional.empty());
        AssertionsForClassTypes.assertThatThrownBy(() -> transactionService.createTransaction(createTransactionCreation()))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(BusinessExceptions.INSTALLMENT_GROUP_NOT_FOUND_BY_ID.getMessage());
    }

    private Optional<InstallmentGroupEntity> createInstallmentGroupEntity() {
        InstallmentGroupEntity installmentGroupEntity = new InstallmentGroupEntity();
        installmentGroupEntity.setId(1L);
        installmentGroupEntity.setNumberOfInstallment(6);
        installmentGroupEntity.setDescription("test");
        installmentGroupEntity.setTotalAmount(BigDecimal.valueOf(1000));
        installmentService.createInstallments(installmentGroupEntity);
        return Optional.of(installmentGroupEntity);
    }

    private TransactionCreation createTransactionCreation() {
        TransactionCreation transactionCreation = new TransactionCreation();
        transactionCreation.setTransactionDate(new Date());
        transactionCreation.setTransactionType(1L);
        transactionCreation.setAmount(BigDecimal.valueOf(1000));
        transactionCreation.setPaymentType(1L);
        transactionCreation.setInstallmentGroupId(1L);
        transactionCreation.setDescription("Test");
        return transactionCreation;
    }

    private TransactionEntity createTransactionEntity(BigDecimal amount) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(amount);
        return transactionEntity;
    }

    private void addTransactionToFirstInstallment(Optional<InstallmentGroupEntity> installmentGroupEntity, BigDecimal amount){
        List<InstallmentEntity> installmentEntityList = new ArrayList<>(installmentGroupEntity.get().getInstallments());
        installmentEntityList.sort(Comparator.comparing(InstallmentEntity::getInstallmentNumber));
        installmentEntityList.get(0).addTransaction(createTransactionEntity(amount));
    }
}
