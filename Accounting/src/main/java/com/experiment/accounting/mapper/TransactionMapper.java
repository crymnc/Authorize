package com.experiment.accounting.mapper;

import com.experiment.accounting.domain.Transaction;
import com.experiment.accounting.domain.TransactionCreation;
import com.experiment.accounting.entity.PaymentTypeEntity;
import com.experiment.accounting.entity.TransactionEntity;
import com.experiment.accounting.entity.TransactionTypeEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public class TransactionMapper {

    public Transaction toModel(TransactionEntity transactionEntity){
        if(transactionEntity == null)
            return null;

        Transaction transaction = new Transaction();
        transaction.setId(transactionEntity.getId());
        transaction.setTransactionType(transactionEntity.getTransactionType().getId());
        transaction.setTransactionDate(transactionEntity.getTransactionDate());
        transaction.setAmount(transactionEntity.getAmount());
        transaction.setDescription(transactionEntity.getDescription());
        transaction.setCancelDate(transactionEntity.getCancelDate());
        transaction.setPaymentType(transactionEntity.getPaymentType().getId());
        transaction.setCreatedAt(transactionEntity.getCreatedAt());
        transaction.setCreatedBy(transactionEntity.getCreatedBy());

        return transaction;
    }

    public TransactionEntity toEntity(TransactionCreation transactionCreation){
        if(transactionCreation == null)
            return null;

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionDate(transactionCreation.getTransactionDate());
        transactionEntity.setDescription(transactionCreation.getDescription());

        TransactionTypeEntity transactionTypeEntity = new TransactionTypeEntity();
        transactionTypeEntity.setId(transactionCreation.getTransactionType());
        transactionEntity.setTransactionType(transactionTypeEntity);

        PaymentTypeEntity paymentTypeEntity = new PaymentTypeEntity();
        paymentTypeEntity.setId(transactionCreation.getPaymentType());
        transactionEntity.setPaymentType(paymentTypeEntity);

        return transactionEntity;
    }

    public List<Transaction> toModelList(Set<TransactionEntity> transactionEntitySet){
        if(transactionEntitySet == null)
            return null;
        return transactionEntitySet.stream().map(this::toModel).collect(Collectors.toList());
    }
}
