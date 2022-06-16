package com.github.assemblathe1.kotlinstore.services

import com.github.assemblathe1.kotlinstore.dto.Cart
import com.github.assemblathe1.kotlinstore.dto.OrderItemDto
import com.github.assemblathe1.kotlinstore.exceptions.exceptions.ResourceNotFoundException
import com.github.assemblathe1.kotlinstore.repositories.CartRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class CartService @Autowired constructor(
    val cartRepository: CartRepository,
    val productsService: ProductService,
    val userService: UserService,
) {

    fun generateCartUuid(): String = cartRepository.save(
        Cart(
            items = mutableListOf(
                OrderItemDto(
                    productId = "62a4d2e1345a6f18e0da6bb0",
                    productTitle = "abc",
                    quantity = 10,
                    pricePerProduct = BigDecimal.ONE,
                    price = BigDecimal.TEN
                )
            )
        )
    ).id.toString()

    fun getCurrentCartById(id: String): Cart? {
        val cart = cartRepository.findById(ObjectId(id)).orElseThrow { ResourceNotFoundException("Cart was not found") }
        println(cart)
        return cart
    }

    fun getCurrentCartByUserName(username: String): Cart? = userService.findByUsername(username).cart

    @Transactional
    fun addProductToCartById(uuid: String, productId: String) {
        productsService.findById(productId)?.let { product ->
            val currentCart = getCurrentCartById(uuid)
            currentCart?.add(product)?.run { cartRepository.save(currentCart) }

        }

    }

    fun addProductToCartByUserName(username: String, productId: String) {
        productsService.findById(productId)?.let { product ->
            val currentCart = getCurrentCartByUserName(username)
            currentCart?.add(product)?.run { userService.updateCartForUser(username, currentCart) }

        }
    }

    fun addToCart(currentCartUuid: String?, productId: Long): Any {
        TODO("Not yet implemented")
    }

    fun decrementItem(currentCartUuid: String?, productId: Long) {
        TODO("Not yet implemented")
    }

    fun clearCart(currentCartUuid: String?) {
        TODO("Not yet implemented")
    }

    fun merge(currentCartUuid: String?, currentCartUuid1: String?) {
        TODO("Not yet implemented")
    }

    fun removeItemFromCart(currentCartUuid: String?, productId: Long) {
        TODO("Not yet implemented")
    }

}
