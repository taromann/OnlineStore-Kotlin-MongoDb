package com.github.assemblathe1.kotlinstore.services

import com.github.assemblathe1.kotlinstore.entities.Product
import com.github.assemblathe1.kotlinstore.entities.User
import com.github.assemblathe1.kotlinstore.repositories.ProductRepository
import com.github.assemblathe1.kotlinstore.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(val userRepository: UserRepository) {

    fun getAllUsers(): MutableList<User?> = userRepository.findAll()

}