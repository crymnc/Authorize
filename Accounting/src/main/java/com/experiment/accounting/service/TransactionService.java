package com.experiment.accounting.service;

import com.experiment.accounting.domain.TransactionCreation;
import com.experiment.accounting.entity.InstallmentEntity;
import com.experiment.accounting.entity.InstallmentGroupEntity;
import com.experiment.accounting.entity.TransactionEntity;
import com.experiment.accounting.exception.BusinessExceptions;
import com.experiment.accounting.mapper.TransactionMapper;
import com.experiment.accounting.repository.base.EntityRepository;
import com.experiment.accounting.service.base.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService extends EntityService {
    private final InstallmentGroupService installmentGroupService;
    private final TransactionMapper transactionMapper;

    public TransactionService(EntityRepository entityRepository, TransactionMapper transactionMapper, InstallmentGroupService installmentGroupService) {
        super(entityRepository);
        this.transactionMapper = transactionMapper;
        this.installmentGroupService = installmentGroupService;
    }

    public void createTransaction(TransactionCreation transactionCreation) {
        InstallmentGroupEntity installmentGroupEntity = installmentGroupService.find(transactionCreation.getInstallmentGroupId(), InstallmentGroupEntity.class).orElseThrow(() -> BusinessExceptions.INSTALLMENT_GROUP_NOT_FOUND_BY_ID);
        distributeAmountToInstallments(installmentGroupEntity, transactionCreation);
        installmentGroupService.save(installmentGroupEntity);
    }

    private void distributeAmountToInstallments(InstallmentGroupEntity installmentGroupEntity, TransactionCreation transactionCreation) {
        if(transactionCreation.getAmount().compareTo(BigDecimal.ZERO) > 0)
            createPaymentTransaction(installmentGroupEntity, transactionCreation);
        else
            createRefundTransaction(installmentGroupEntity, transactionCreation);
    }

    private void createRefundTransaction(InstallmentGroupEntity installmentGroupEntity, TransactionCreation transactionCreation) {
        BigDecimal leftRefundAmount = transactionCreation.getAmount();
        if (!CollectionUtils.isEmpty(installmentGroupEntity.getInstallments())) {
            List<InstallmentEntity> reversedOrderedInstallments = installmentGroupEntity.getInstallments().stream().sorted((o1, o2) -> o2.getInstallmentNumber().compareTo(o1.getInstallmentNumber())).collect(Collectors.toList());
            for (InstallmentEntity installmentEntity : reversedOrderedInstallments) {
                if (leftRefundAmount.compareTo(BigDecimal.ZERO) == 0)
                    continue;
                BigDecimal totalTransactionAmount = BigDecimal.ZERO;
                if (!CollectionUtils.isEmpty(installmentEntity.getTransactions()))
                    totalTransactionAmount = installmentEntity.getTransactions().stream()
                            .map(transactionEntity -> transactionEntity.getCancelDate() == null ? transactionEntity.getAmount():BigDecimal.ZERO)
                            .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.FLOOR);

                if (totalTransactionAmount.compareTo(BigDecimal.ZERO) == 0)
                    continue;
                BigDecimal usedAmountForRefund = leftRefundAmount.abs().compareTo(totalTransactionAmount) < 0 ? leftRefundAmount:totalTransactionAmount.multiply(BigDecimal.valueOf(-1L));
                TransactionEntity transactionEntity = transactionMapper.toEntity(transactionCreation);
                transactionEntity.setAmount(usedAmountForRefund);
                transactionEntity.setInstallment(installmentEntity);
                installmentEntity.addTransaction(transactionEntity);
                leftRefundAmount = leftRefundAmount.subtract(usedAmountForRefund);
            }
            installmentGroupEntity.setInstallments(new HashSet<>(reversedOrderedInstallments));
        }
        if (leftRefundAmount.compareTo(BigDecimal.ZERO) != 0) {
            throw BusinessExceptions.PAYMENT_AMOUNT_CANNOT_BE_MORE_OR_LESS_THAN_INST_GROUP_TOTAL_AMOUNT;
        }
    }

    private void createPaymentTransaction(InstallmentGroupEntity installmentGroupEntity, TransactionCreation transactionCreation){
        if (CollectionUtils.isEmpty(installmentGroupEntity.getInstallments()))
            throw BusinessExceptions.INSTALLMENTS_SHOULD_BE_CREATED_FIRST;

        BigDecimal leftPaymentAmount = transactionCreation.getAmount();
        List<InstallmentEntity> orderedInstallments = installmentGroupEntity.getInstallments().stream().sorted(Comparator.comparing(InstallmentEntity::getInstallmentNumber)).collect(Collectors.toList());
        for (InstallmentEntity installmentEntity : orderedInstallments) {
            if (leftPaymentAmount.compareTo(BigDecimal.ZERO) == 0)
                continue;
            BigDecimal totalTransactionAmount = BigDecimal.ZERO;
            if (!CollectionUtils.isEmpty(installmentEntity.getTransactions()))
                totalTransactionAmount = installmentEntity.getTransactions().stream()
                        .map(transactionEntity -> transactionEntity.getCancelDate() == null ? transactionEntity.getAmount():BigDecimal.ZERO)
                        .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.FLOOR);

            BigDecimal leftAmountOnInstallment = installmentEntity.getAmount().subtract(totalTransactionAmount);
            if (leftAmountOnInstallment.compareTo(BigDecimal.ZERO) == 0)
                continue;
            BigDecimal usedAmountForPayment = leftPaymentAmount.compareTo(leftAmountOnInstallment) < 0 ? leftPaymentAmount:leftAmountOnInstallment;
            TransactionEntity transactionEntity = transactionMapper.toEntity(transactionCreation);
            transactionEntity.setAmount(usedAmountForPayment);
            transactionEntity.setInstallment(installmentEntity);
            installmentEntity.addTransaction(transactionEntity);
            leftPaymentAmount = leftPaymentAmount.subtract(usedAmountForPayment);
        }

        if (leftPaymentAmount.compareTo(BigDecimal.ZERO) != 0)
            throw BusinessExceptions.PAYMENT_AMOUNT_CANNOT_BE_MORE_OR_LESS_THAN_INST_GROUP_TOTAL_AMOUNT;

    }
}
