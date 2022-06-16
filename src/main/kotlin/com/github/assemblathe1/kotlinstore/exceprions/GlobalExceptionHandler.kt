package com.github.assemblathe1.kotlinstore.exceprions

import com.github.assemblathe1.kotlinstore.exceprions.exceptions.ValidationException
import com.github.assemblathe1.kotlinstore.exceptions.errors.FieldsValidationError
import com.github.assemblathe1.kotlinstore.exceptions.errors.ResourceNotFoundError
import com.github.assemblathe1.kotlinstore.exceptions.exceptions.ResourceNotFoundException
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

    @ExceptionHandler
    fun catchResourceNotFoundException(e: ResourceNotFoundException): ResponseEntity<ResourceNotFoundError> {
        KotlinLogging.logger {}.warn { e.message }
        return ResponseEntity(ResourceNotFoundError(HttpStatus.NOT_FOUND.value(), e.message), HttpStatus.NOT_FOUND)
    }
}