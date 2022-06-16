package com.github.assemblathe1.kotlinstore.entities

import com.github.assemblathe1.kotlinstore.dto.Cart
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId

@Document(collection = "users")
data class User(
    @MongoId(value = FieldType.OBJECT_ID)
    var id: ObjectId? = null,
    var userName: String,
    var password: String,
    var email: String,
    var age: Byte,
    private var roles: MutableSet<String> = mutableSetOf("user"),
    var cart: Cart? = null
) {
    fun addRole(newRole: Role) = this.roles.add(newRole.name)
    fun removeRole(deletingRole: Role) = this.roles.removeIf { it == deletingRole.name }
    var rolesNames = this.roles

}