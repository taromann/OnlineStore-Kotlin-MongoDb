package com.github.assemblathe1.kotlinstore.validators

import com.github.assemblathe1.kotlinstore.dto.ProductDto
import com.github.assemblathe1.kotlinstore.exceprions.exceptions.ValidationException
import org.bson.types.ObjectId
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class ProductValidator {
    fun validateProductDto(productDto: ProductDto) {
        val errors = mutableListOf<String>()
        validateTitleAndPrice(productDto.price < BigDecimal.ONE, errors, "Цена продукта не может быть меньше 1")
        validateTitleAndPrice(productDto.title.isEmpty(), errors, "Продукт не может иметь пустое название")
        validateId(productDto.id.toString(), errors)
        if (errors.isNotEmpty()) throw ValidationException(errors)
    }

    private fun validateTitleAndPrice(predicate: Boolean, errors: MutableList<String>, message: String) {
        if (predicate) errors.add(message)
    }

    fun validateId(id: String, listOfErrors: MutableList<String>? = null) = try {
        ObjectId(id)
    } catch (e: IllegalArgumentException) {
        listOfErrors?.let { listOfErrors.add("Некорректный формат ID") } ?: throw ValidationException(mutableListOf("Некорректный формат ID"))
    }
}