package com.github.assemblathe1.kotlinstore.services

import com.github.assemblathe1.kotlinstore.dto.OrderDetailsDto
import com.github.assemblathe1.kotlinstore.entities.Order
import com.github.assemblathe1.kotlinstore.entities.OrderItem
import com.github.assemblathe1.kotlinstore.repositories.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService @Autowired constructor(val cartService: CartService, val productService: ProductService, val orderRepository: OrderRepository) {
    fun createOrder(username: String, orderDetailsDto: OrderDetailsDto) {
        val userCart = cartService.getCurrentCartByUserName(username)
        val order = Order(
            username = username,
            address = orderDetailsDto.address,
            phone = orderDetailsDto.phone,
            items = userCart.items.map { it ->
                OrderItem(
                    product = productService.findById(it.productId),
                    quantity = it.quantity,
                    pricePerProduct = it.pricePerProduct,
                    price = it.price
                )
            }.toMutableList()
        )
        orderRepository.save(order)
        cartService.clearCartByUserName(username)

    }
}