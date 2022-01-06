package com.experiment.accounting.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class BusinessExceptionHandler {
    private static final Logger logger = LogManager.getLogger(BusinessExceptionHandler.class);

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity throwBusinessException(BusinessException businessException) {
        logger.error(businessException);
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(businessException.getMessage())
                .debugMessage(businessException.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity throwUnexpectedException(Exception exception) {
        logger.error(exception);
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .debugMessage(exception.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
