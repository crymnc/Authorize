package com.experiment.inventory.exception

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class BusinessExceptionHandler {

    private val logger: Logger = LogManager.getLogger(BusinessExceptionHandler::class.java)

    @ExceptionHandler(value = [BusinessException::class])
    fun throwBusinessException(businessException: BusinessException): ResponseEntity<*>? {
        logger.error(businessException)
        val apiError: ApiError = ApiError()
        apiError.status = HttpStatus.INTERNAL_SERVER_ERROR
        apiError.message = businessException.message
        apiError.debugMessage = businessException.localizedMessage
        apiError.timestamp = LocalDateTime.now()
        return ResponseEntity<Any?>(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [Exception::class])
    fun throwUnexpectedException(exception: Exception): ResponseEntity<*>? {
        logger.error(exception)
        val apiError: ApiError = ApiError()
            apiError.status = HttpStatus.INTERNAL_SERVER_ERROR
            apiError.message = exception.message
            apiError.debugMessage = exception.localizedMessage
            apiError.timestamp = LocalDateTime.now()
        return ResponseEntity<Any?>(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}