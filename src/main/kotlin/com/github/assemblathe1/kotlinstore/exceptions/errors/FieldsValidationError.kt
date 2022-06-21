package com.github.assemblathe1.kotlinstore.exceptions.errors

class FieldsValidationError(
    val errorFieldsMessages: List<String?>, statusCode: Int? = null,
    message: String? = null
) : Error(statusCode, message)