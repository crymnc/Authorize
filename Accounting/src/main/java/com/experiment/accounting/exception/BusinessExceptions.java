package com.experiment.accounting.exception;

public class BusinessExceptions {

    public static final BusinessException CONSTANT_NAME_IS_NOT_ACCEPTABLE = new BusinessException("Constant name is not acceptable");
    public static final BusinessException ACCOUNT_NOT_FOUND_BY_USER_ID = new BusinessException("Account not found by user id");
    public static final BusinessException TRANSACTION_TYPE_NOT_FOUND_BY_ID = new BusinessException("Transaction type not found by id");
    public static final BusinessException USER_ID_CANNOT_BE_NULL = new BusinessException("User ID cannot be null");
    public static final BusinessException USER_ALREADY_HAS_AN_ACCOUNT = new BusinessException("User already has an account");
    public static final BusinessException INSTALLMENT_GROUP_NOT_FOUND_BY_ID = new BusinessException("Installment group not found by id");
    public static final BusinessException INSTALLMENT_GROUP_NOT_FOUND_BY_USER_ID = new BusinessException("Installment group not found by user id");
    public static final BusinessException INSTALLMENTS_NOT_FOUND_BY_GROUP_ID = new BusinessException("Installments not found by group id");
    public static final BusinessException INSTALLMENT_NOT_FOUND_BY_ID = new BusinessException("Installment not found by id");
    public static final BusinessException PAYMENT_AMOUNT_CANNOT_BE_MORE_OR_LESS_THAN_INST_GROUP_TOTAL_AMOUNT = new BusinessException("Payment amount cannot be more or less than left total amount of group");
    public static final BusinessException TRANSACTION_NOT_FOUND_BY_ID = new BusinessException("Transaction not found by id");
    public static final BusinessException USER_NOT_FOUND_BY_ID = new BusinessException("User not found by id");
    public static final BusinessException INSTALLMENTS_SHOULD_BE_CREATED_FIRST = new BusinessException("Installments should be create first");
}
