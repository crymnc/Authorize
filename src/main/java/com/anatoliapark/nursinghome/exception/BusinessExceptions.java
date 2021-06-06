package com.anatoliapark.nursinghome.exception;

public class BusinessExceptions {

    public static final BusinessException CONSTANT_NAME_IS_NOT_ACCEPTABLE = new BusinessException("Constant name is not acceptable");
    public static final BusinessException USER_NOT_FOUND = new BusinessException("User not found");
    public static final BusinessException USER_ALREADY_EXISTS = new BusinessException("UserEntity already exists");

}
