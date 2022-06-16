package com.github.assemblathe1.kotlinstore.converters

import com.geekbrains.spring.web.entities.Product
import com.github.assemblathe1.kotlinstore.dto.OrderItemDto
import com.github.assemblathe1.kotlinstore.entities.OrderItem
import org.springframework.stereotype.Component

@Component
class OrderItemConverter {
    fun dtoToEntity(orderItemDto: OrderItemDto): Nothing = throw UnsupportedOperationException()
    fun entityToDto(orderItem: OrderItem): OrderItemDto {
        val orderItemDto = OrderItemDto(
            Product(
                orderItem.product.id,
                orderItem.product.title,
                orderItem.product.price,
            )
        )
        orderItemDto.quantity = orderItem.quantity
        orderItemDto.pricePerProduct = orderItem.pricePerProduct
        return orderItemDto
    }
}