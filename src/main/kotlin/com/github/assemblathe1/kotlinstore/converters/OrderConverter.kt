package com.github.assemblathe1.kotlinstore.converters

import com.github.assemblathe1.kotlinstore.dto.OrderDto
import com.github.assemblathe1.kotlinstore.entities.Order
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrderConverter @Autowired constructor(val orderItemConverter: OrderItemConverter) {
    fun dtoToEntity(orderDto: OrderDto): Order = throw UnsupportedOperationException()

    fun entityToDto(order: Order): OrderDto {
        val out = OrderDto()
        out.id = order.id.toString()
        out.address = order.address
        out.phone = order.phone
        out.totalPrice = order.totalPrice
        out.username = order.username
        out.items = order.items.map { orderItemConverter.entityToDto(it) }.toList()
        return out
    }
}