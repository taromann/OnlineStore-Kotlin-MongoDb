package com.github.assemblathe1.kotlinstore.repositories

import com.github.assemblathe1.kotlinstore.dto.OrderItemDto
import com.github.assemblathe1.kotlinstore.entities.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Repository
interface UserRepository : MongoRepository<User?, String?> {
    @Query("{'userName': ?0}")
    fun findByUsername(username: String): User?

}