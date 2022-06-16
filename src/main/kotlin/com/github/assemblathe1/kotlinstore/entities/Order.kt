package com.github.assemblathe1.kotlinstore.entities

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.math.BigDecimal

@Document(collection = "orders")
class Order(
    @MongoId(value = FieldType.OBJECT_ID)
    var id: ObjectId? = null,
    var username: String,
    var items: MutableList<OrderItem>,
    var address: String,
    var phone: String
) {
    var totalPrice: BigDecimal = items.map { it.pricePerProduct.multiply(BigDecimal(it.quantity)) }.sumOf { it }


}

