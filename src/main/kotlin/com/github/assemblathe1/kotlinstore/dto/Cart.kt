package com.github.assemblathe1.kotlinstore.dto

import com.geekbrains.spring.web.entities.Product
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.math.BigDecimal

@Document(collection = "carts")
class Cart(
    @MongoId(value = FieldType.OBJECT_ID)
    var id: ObjectId? = null,
    private val items: MutableList<OrderItemDto> = mutableListOf(),
    private var totalPrice: BigDecimal = BigDecimal.ZERO
) {
    fun add(product: Product) {
        if (incrementProduct(product)) return
        items.add(OrderItemDto(product))
        recalculate()
    }

    private fun incrementProduct(product: Product): Boolean {
        items.forEach { orderItemDto ->
            if (orderItemDto.productId == product.id.toString()) {
                orderItemDto.changeQuantity(1)
                recalculate()
                return true
            }
        }
        return false
    }

    fun decrement(productId: String) {
        val iter = items.iterator()
        while (iter.hasNext()) {
            val o = iter.next()
            if (o.productId == productId) {
                o.changeQuantity(-1)
                if (o.quantity <= 0) iter.remove()
                recalculate()
                return
            }
        }

    }

    fun remove(productId: String) = items.removeIf { it.productId == productId }.run { recalculate() }

    fun clear() = items.clear().run { totalPrice = BigDecimal.ZERO }

    private fun recalculate() {
        totalPrice = items.map { it.price }.toList().sumOf { it }
    }

    fun merge(anotherCart: Cart) {
        anotherCart.items.forEach { anotherItemDto ->
            this.findItemDto(anotherItemDto)
                ?.let { it.quantity += anotherItemDto.quantity }
                ?: this.items.add(anotherItemDto)
        }
        recalculate()
        anotherCart.clear()
    }


    private fun Cart.findItemDto(orderItemDto: OrderItemDto): OrderItemDto? =
        this.items.firstOrNull { it.equalByProductIdTo(orderItemDto) }


}