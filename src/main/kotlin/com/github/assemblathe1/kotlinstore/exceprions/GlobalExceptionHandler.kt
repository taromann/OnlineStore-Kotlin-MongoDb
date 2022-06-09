package com.github.assemblathe1.kotlinstore.exceprions

import com.github.assemblathe1.kotlinstore.exceprions.errors.FieldsValidationError
import com.github.assemblathe1.kotlinstore.exceprions.exceptions.ValidationException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler
    fun catchValidationException(e: ValidationException): ResponseEntity<FieldsValidationError> {
        KotlinLogging.logger {}.warn { e.message }
        return ResponseEntity(FieldsValidationError(e.errorFieldsMessages), HttpStatus.BAD_REQUEST)
    }
}