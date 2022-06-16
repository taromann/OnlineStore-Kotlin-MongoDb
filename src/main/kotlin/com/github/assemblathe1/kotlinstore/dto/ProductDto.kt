package ccom.github.assemblathe1.kotlinstore.dto

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.math.BigDecimal

// TODO make not null
data class ProductDto(
    var id: String? = null,
    var title: String,
    var price: BigDecimal
)