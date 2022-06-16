package com.github.assemblathe1.kotlinstore.entities

import org.bson.types.ObjectId
import java.math.BigDecimal

class Order(
    var id: ObjectId? = null,
    val username: String,
    val items: MutableList<OrderItem>,
    val totalPrice: BigDecimal,
    val address: String,
    val phone: String
) {
}