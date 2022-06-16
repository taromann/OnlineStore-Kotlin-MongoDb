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

    fun getCurrentCartByUserName(username: String): Cart = userService.findByUsername(username).cart ?: Cart()

    @Transactional
    fun addProductToCartById(uuid: String, productId: String) {
        productsService.findById(productId).let { product ->
            val currentCart = getCurrentCartById(uuid)
            currentCart?.add(product)?.run { cartRepository.save(currentCart) }
        }
    }

    fun addProductToCartByUserName(username: String, productId: String) {
        productsService.findById(productId).let { product ->
            val currentCart = getCurrentCartByUserName(username)
            currentCart?.add(product)?.run { userService.updateCartForUser(username, currentCart) }
        }
    }

    fun decrementItemFromCartById(uuid: String, productId: String) {
        productsService.findById(productId).let { product ->
            val currentCart = getCurrentCartById(uuid)
            currentCart?.decrement(product.id.toString())?.run { cartRepository.save(currentCart) }
        }
    }

    fun decrementItemFromCartByUserName(username: String, productId: String) {
        productsService.findById(productId).let { product ->
            val currentCart = getCurrentCartByUserName(username)
            currentCart?.decrement(product.id.toString())?.run { userService.updateCartForUser(username, currentCart) }
        }
    }

    fun removeItemFromCartById(uuid: String, productId: String) {
        productsService.findById(productId).let { product ->
            val currentCart = getCurrentCartById(uuid)
            currentCart?.remove(product.id.toString())?.run { cartRepository.save(currentCart) }
        }
    }

    fun removeItemFromCartByUserName(username: String, productId: String) {
        productsService.findById(productId).let { product ->
            val currentCart = getCurrentCartByUserName(username)
            currentCart?.remove(product.id.toString())?.run { userService.updateCartForUser(username, currentCart) }
        }
    }

    fun merge(username: String, uuid: String) {
        getCurrentCartById(uuid)?.run {
            val userCart = getCurrentCartByUserName(username) ?: Cart()
            userCart.merge(this)
            userService.updateCartForUser(username, userCart)
        }

    }

    fun clearCartByUserName(username: String) {
        getCurrentCartByUserName(username).run {
            userService.updateCartForUser(username, Cart())
        }
    }

    fun clearCartById(uuid: String) {
        getCurrentCartById(uuid)?.run { cartRepository.save(Cart(id = this.id)) }
    }

}
