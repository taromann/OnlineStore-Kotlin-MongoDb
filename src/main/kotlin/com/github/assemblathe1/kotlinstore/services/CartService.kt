package com.github.assemblathe1.kotlinstore.services

import com.github.assemblathe1.kotlinstore.dto.Cart
import com.github.assemblathe1.kotlinstore.repositories.CartRepository
import com.github.assemblathe1.kotlinstore.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CartService @Autowired constructor(
    val productsService: ProductService,
    val cartRepository: CartRepository,
    val userRepository: UserRepository
) {

    fun generateCartUuid(): String = cartRepository.save(Cart()).id.toString()

    fun getCurrentCartById(id: String): Cart? = cartRepository.findById(id).get()

    fun getCurrentCartByUserName(username: String): Cart? = userRepository.findByUsername(username)?.cart

    fun addProductToCartByUserName(username: String) {
        TODO("Not yet implemented")
    }

    fun addProductToCartById(uuid: String) {

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
