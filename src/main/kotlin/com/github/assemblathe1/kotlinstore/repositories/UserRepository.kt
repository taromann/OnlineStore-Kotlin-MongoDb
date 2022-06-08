package com.github.assemblathe1.kotlinstore.repositories

import com.github.assemblathe1.kotlinstore.entities.Product
import com.github.assemblathe1.kotlinstore.entities.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User?, String?> {
}