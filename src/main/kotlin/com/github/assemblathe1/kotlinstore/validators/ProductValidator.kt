package com.github.assemblathe1.kotlinstore.validators


import ccom.github.assemblathe1.kotlinstore.dto.ProductDto
import com.github.assemblathe1.kotlinstore.exceptions.exceptions.ValidationException
import org.bson.types.ObjectId
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class ProductValidator {

    val errors = mutableListOf<String>()
    fun validateAllProductDto(productDto: ProductDto) {
        validateTitleAndPriceDto(productDto)
        validateIdDto(productDto.id.toString(), errors)
        if (errors.isNotEmpty()) throw ValidationException(errors)
    }

    fun validateTitleAndPriceDto(productDto: ProductDto) {
        if (productDto.price < BigDecimal.ONE) errors.add("Цена продукта не может быть меньше 1")
        if (productDto.title.isEmpty()) errors.add("Продукт не может иметь пустое название")
    }

    fun validateIdDto(id: String, listOfErrors: MutableList<String>? = null) = try {
        ObjectId(id)
    } catch (e: IllegalArgumentException) {
        listOfErrors?.let { listOfErrors.add("Некорректный формат ID") } ?: throw ValidationException(mutableListOf("Некорректный формат ID"))
    }
}