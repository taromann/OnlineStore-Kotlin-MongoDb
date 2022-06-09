package com.github.assemblathe1.kotlinstore.validators

import com.github.assemblathe1.kotlinstore.dto.ProductDto
import com.github.assemblathe1.kotlinstore.exceprions.exceptions.ValidationException
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class ProductValidator {
    fun validate(productDto: ProductDto) {
        val errors = mutableListOf<String>()
        checkField(productDto.price < BigDecimal.ONE, errors, "Цена продукта не может быть меньше 1")
        checkField(productDto.title.isEmpty(), errors, "Продукт не может иметь пустое название")
        if (errors.isNotEmpty()) throw ValidationException(errors)
    }

    private fun checkField(predicate: Boolean, errors: MutableList<String>, message: String) {
        if (predicate) errors.add(message)
    }
}