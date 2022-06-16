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
        username?.let { cartService.getCurrentCartByUserName(username) } ?: cartService.getCurrentCartById(uuid)


    @GetMapping("/{uuid}/add/{productId}")
    fun addProductToCart(
        @RequestHeader(required = false) username: String?,
        @PathVariable uuid: String,
        @PathVariable productId: String
    ) {
        username?.let { cartService.addProductToCartByUserName(username, productId) } ?: cartService.addProductToCartById(uuid, productId)
    }


//    @GetMapping("/{uuid}/decrement/{productId}")
//    fun decrement(
//        @RequestHeader(required = false) username: String?,
//        @PathVariable uuid: String,
//        @PathVariable productId: Long
//    ) {
//        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId)
//    }
//
//    @GetMapping("/{uuid}/remove/{productId}")
//    fun remove(
//        @RequestHeader(required = false) username: String?,
//        @PathVariable uuid: String,
//        @PathVariable productId: Long
//    ) {
//        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId)
//    }
//
//
//    @GetMapping("/{uuid}/clear")
//    fun clear(@RequestHeader(required = false) username: String?, @PathVariable uuid: String) {
//        cartService.clearCart(getCurrentCartUuid(username, uuid))
//    }
//
//    @GetMapping("/{uuid}/merge")
//    fun merge(@RequestHeader(required = false) username: String?, @PathVariable uuid: String) {
//        cartService.merge(
//            getCurrentCartUuid(username = username, uuid = uuid),
//            getCurrentCartUuid(uuid = uuid)
//        )
//    }
}