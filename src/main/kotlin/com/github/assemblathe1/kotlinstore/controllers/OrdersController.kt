package com.github.assemblathe1.kotlinstore.controllers

import com.github.assemblathe1.kotlinstore.converters.OrderConverter
import com.github.assemblathe1.kotlinstore.dto.OrderDetailsDto
import com.github.assemblathe1.kotlinstore.entities.Order
import com.github.assemblathe1.kotlinstore.services.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/orders")
class OrdersController @Autowired constructor(val orderService: OrderService, orderConverter: OrderConverter) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestHeader username: String, @RequestBody orderDetailsDto: OrderDetailsDto) {
        orderService.createOrder(username, orderDetailsDto)

    }


}