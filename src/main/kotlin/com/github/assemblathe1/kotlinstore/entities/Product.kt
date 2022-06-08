package com.github.assemblathe1.kotlinstore.entities

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document(collection = "products")
data class Product(
    @Id var id: ObjectId? = null,
    var title: String? = null,
    var price: BigDecimal? = null
)