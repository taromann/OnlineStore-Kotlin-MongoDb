package com.geekbrains.spring.web.entities;
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.math.BigDecimal

@Document(collection = "products")
data class Product(
    @MongoId(value = FieldType.OBJECT_ID)
    var id: ObjectId? = null,
    var title: String,
    var price: BigDecimal
)