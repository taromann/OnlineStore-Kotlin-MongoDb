package com.github.assemblathe1.kotlinstore.repositories

import com.geekbrains.spring.web.entities.Product
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : MongoRepository<Product?, ObjectId?>