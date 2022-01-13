package com.experiment.inventory.exception

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class BusinessExceptionHandler {

    private val logger: Logger = LogManager.getLogger(BusinessExceptionHandler::class.java)

    @Value("\${isDebug}")
    var isDebug = true
    @ExceptionHandler(value = [BusinessException::class])
    fun throwBusinessException(businessException: BusinessException): ResponseEntity<*>? {
        logger.error(businessException)
        val apiError: ApiError = ApiError()
        apiError.status = "Business Exception"
        apiError.message = businessException.message
        if(isDebug)
            apiError.debugMessage = businessException.localizedMessage
        apiError.timestamp = LocalDateTime.now()
        return ResponseEntity<Any?>(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun throwValidationException(exception: MethodArgumentNotValidException): ResponseEntity<*>? {
        logger.error(exception)
        val apiError: ApiError = ApiError()
        apiError.status = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase
        apiError.message = exception.fieldError?.defaultMessage
        if(isDebug)
            apiError.debugMessage = exception.localizedMessage
        apiError.timestamp = LocalDateTime.now()
        return ResponseEntity<Any?>(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [Exception::class])
    fun throwUnexpectedException(exception: Exception): ResponseEntity<*>? {
        logger.error(exception)
        val apiError: ApiError = ApiError()
            apiError.status = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase
            apiError.message = exception.message
            if(isDebug)
                apiError.debugMessage = exception.localizedMessage
            apiError.timestamp = LocalDateTime.now()
        return ResponseEntity<Any?>(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}