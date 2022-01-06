package com.experiment.accounting.utils;

import com.experiment.accounting.entity.PaymentTypeEntity;
import com.experiment.accounting.entity.TransactionTypeEntity;
import com.experiment.accounting.exception.BusinessExceptions;

import java.util.ArrayList;
import java.util.List;

public class ConstantUtils {
    private static final List<ConstantReference> constants = new ArrayList<>();

    static {
        ConstantReference paymentTypeReference = new ConstantReference();
        ConstantReference transactionTypeReference = new ConstantReference();

        paymentTypeReference.setClazz(PaymentTypeEntity.class);
        paymentTypeReference.setName("payment-type");
        paymentTypeReference.setDesc("Payment Type");

        transactionTypeReference.setClazz(TransactionTypeEntity.class);
        transactionTypeReference.setName("transaction-type");
        transactionTypeReference.setDesc("Transaction Type");

        constants.add(paymentTypeReference);
        constants.add(transactionTypeReference);
    }

    public static ConstantReference getReferenceByName(String name){
        return constants.stream().filter(constantHierarchy -> constantHierarchy.name.equals(name)).findFirst().orElseThrow(() -> BusinessExceptions.CONSTANT_NAME_IS_NOT_ACCEPTABLE);
    }

}
