package com.github.assemblathe1.kotlinstore.dto

import com.geekbrains.spring.web.entities.Product
import java.math.BigDecimal

data class OrderItemDto(
  val product: Product
) {
    var productId: String = product.id.toString()
    var productTitle: String = product.title
    var quantity: Int = 1
    var pricePerProduct: BigDecimal = product.price
    var price: BigDecimal = product.price

    fun changeQuantity(delta: Int) {
        quantity += delta
        price = pricePerProduct.multiply(quantity.toBigDecimal())
    }
}

fun OrderItemDto.equalByProductIdTo(orderItemDto: OrderItemDto) = this.productId == orderItemDto.productId
