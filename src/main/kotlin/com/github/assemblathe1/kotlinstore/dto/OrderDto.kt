package com.github.assemblathe1.kotlinstore.dto

import java.math.BigDecimal

class OrderDto {
    var id: String? = null
    var username: String? = null
    var items: List<OrderItemDto>? = null
    var totalPrice: BigDecimal? = null
    var address: String? = null
    var phone: String? = null
}