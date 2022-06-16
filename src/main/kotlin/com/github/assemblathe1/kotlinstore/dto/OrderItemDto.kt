package com.github.assemblathe1.kotlinstore.dto

import java.math.BigDecimal

class OrderItemDto(
    var productId: String,
    var productTitle: String,
    var quantity: Int,
    var pricePerProduct: BigDecimal,
    var price: BigDecimal
) {
    fun changeQuantity(delta: Int) {
        quantity += delta
        price = pricePerProduct.multiply(quantity.toBigDecimal())
    }
}

fun OrderItemDto.equalByProductIdTo(orderItemDto: OrderItemDto) = this.productId == orderItemDto.productId
