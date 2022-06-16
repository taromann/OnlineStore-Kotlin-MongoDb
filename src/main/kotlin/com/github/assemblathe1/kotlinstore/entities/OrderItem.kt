package com.github.assemblathe1.kotlinstore.entities

import com.geekbrains.spring.web.entities.Product
import org.bson.types.ObjectId
import java.math.BigDecimal

class OrderItem(
    var id: ObjectId? = null,
    val product: Product,
    val order: Order,
    val quantity: Int,
    val pricePerProduct: BigDecimal,
    val price: BigDecimal


)