package com.github.assemblathe1.kotlinstore.entities

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "roles")
data class Role(
    var id: ObjectId? = null,
    var name: String,
)