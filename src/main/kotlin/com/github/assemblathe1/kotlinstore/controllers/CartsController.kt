package com.github.assemblathe1.kotlinstore.controllers

import com.github.assemblathe1.kotlinstore.dto.Cart
import com.github.assemblathe1.kotlinstore.dto.StringResponse
import com.github.assemblathe1.kotlinstore.services.CartService
import com.github.assemblathe1.kotlinstore.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/cart")
class CartsController @Autowired constructor(val cartService: CartService, val productsService: ProductService) {

    @GetMapping("/generate")
    fun generateCart(): StringResponse = StringResponse(cartService.generateCartUuid())

    @GetMapping("/{uuid}")
    fun getCart(@RequestHeader(required = false) username: String?, @PathVariable uuid: String): Cart? =
        username?.let { cartService.getCurrentCartByUserName(username) }
            ?: cartService.getCurrentCartById(uuid)


    @GetMapping("/{uuid}/add/{productId}")
    fun addProductToCart(
        @RequestHeader(required = false) username: String?,
        @PathVariable uuid: String,
        @PathVariable productId: String
    ) {
        username?.let { cartService.addProductToCartByUserName(username, productId) }
            ?: cartService.addProductToCartById(uuid, productId)
    }


    @GetMapping("/{uuid}/decrement/{productId}")
    fun decrement(
        @RequestHeader(required = false) username: String?,
        @PathVariable uuid: String,
        @PathVariable productId: String
    ) {
        username?.let { cartService.decrementItemFromCartByUserName(username, productId) }
            ?: cartService.decrementItemFromCartById(uuid, productId)
    }

    //
    @GetMapping("/{uuid}/remove/{productId}")
    fun remove(
        @RequestHeader(required = false) username: String?,
        @PathVariable uuid: String,
        @PathVariable productId: String
    ) {
        username?.let { cartService.removeItemFromCartByUserName(username, productId) }
            ?: cartService.removeItemFromCartById(uuid, productId)
    }

    @GetMapping("/{uuid}/merge")
    fun merge(@RequestHeader username: String, @PathVariable uuid: String) {
        cartService.merge(username, uuid)
    }

    @GetMapping("/{uuid}/clear")
    fun clear(@RequestHeader(required = false) username: String?, @PathVariable uuid: String) {
        username?.let { cartService.clearCartByUserName(username) }
            ?: cartService.clearCartById(uuid)
    }
}