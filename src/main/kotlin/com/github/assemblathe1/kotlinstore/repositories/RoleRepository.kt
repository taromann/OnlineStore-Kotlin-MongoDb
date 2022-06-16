package com.github.assemblathe1.kotlinstore.repositories

import com.github.assemblathe1.kotlinstore.entities.Role
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : MongoRepository<Role?, String?> {
    @Query("{'name': ?0}")
    fun findRoleByTitle(title: String): Role?
}

