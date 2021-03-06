package com.github.assemblathe1.kotlinstore.converters


import ccom.github.assemblathe1.kotlinstore.dto.ProductDto
import com.geekbrains.spring.web.entities.Product
import org.bson.types.ObjectId
import org.springframework.stereotype.Component

@Component
class ProductConverter {
    fun dtoToEntity(productDto: ProductDto) =
        Product(productDto.id?.let { ObjectId(it) }, productDto.title, productDto.price)

    fun entityToDto(product: Product) = ProductDto(product.id.toString(), product.title, product.price)
}