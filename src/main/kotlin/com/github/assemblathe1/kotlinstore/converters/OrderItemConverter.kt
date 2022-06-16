package com.github.assemblathe1.kotlinstore.converters

import com.github.assemblathe1.kotlinstore.dto.OrderItemDto
import com.github.assemblathe1.kotlinstore.entities.OrderItem
import org.springframework.stereotype.Component

@Component
class OrderItemConverter {
    fun dtoToEntity(orderItemDto: OrderItemDto): Nothing = throw UnsupportedOperationException()
    fun entityToDto(orderItem: OrderItem): OrderItemDto {
        val orderItemDto = OrderItemDto(
            productId = orderItem.product.id.toString(),
            productTitle = orderItem.product.title,
            quantity =  orderItem.quantity,
            pricePerProduct = orderItem.product.price,
            price = orderItem.product.price
        )
        orderItemDto.quantity = orderItem.quantity
        orderItemDto.pricePerProduct = orderItem.pricePerProduct
        return orderItemDto
    }
}