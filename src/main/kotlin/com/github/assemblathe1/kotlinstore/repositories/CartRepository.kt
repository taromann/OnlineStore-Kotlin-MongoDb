package com.github.assemblathe1.kotlinstore.repositories

import com.github.assemblathe1.kotlinstore.dto.Cart
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CartRepository : MongoRepository<Cart?, ObjectId?> {
}