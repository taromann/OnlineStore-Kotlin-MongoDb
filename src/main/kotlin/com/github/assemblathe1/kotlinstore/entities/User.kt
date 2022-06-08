package com.github.assemblathe1.kotlinstore.entities

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(
    @Id var id: ObjectId? = null,
    var userName: String,
    var age: Byte,
    var email: String
)