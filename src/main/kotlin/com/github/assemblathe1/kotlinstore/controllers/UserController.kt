package com.github.assemblathe1.kotlinstore.controllers

import com.github.assemblathe1.kotlinstore.entities.Product
import com.github.assemblathe1.kotlinstore.entities.User
import com.github.assemblathe1.kotlinstore.services.ProductService
import com.github.assemblathe1.kotlinstore.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController @Autowired constructor(val userService: UserService) {

    @GetMapping
    fun getAllUsers(): MutableList<User?> = userService.getAllUsers()

}