package com.github.assemblathe1.kotlinstore.services

import com.github.assemblathe1.kotlinstore.dto.ProductDto
import com.github.assemblathe1.kotlinstore.entities.Product
import com.github.assemblathe1.kotlinstore.repositories.ProductRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService @Autowired constructor(val productRepository: ProductRepository) {

    fun getAll(minPrice: Int?, maxPrice: Int?, titlePart: String?, page: Int): Page<Product?> {
        // TODO specifications
        return productRepository.findAll(PageRequest.of(page - 1, 10))
    }

    fun create(product: Product): Product = productRepository.insert(product)

    fun getById(id: String): Product? = productRepository.findByIdOrNull(ObjectId(id))

    @Transactional
    fun update(newProduct: Product): Product? {
        return productRepository.findByIdOrNull(newProduct.id)?.apply {
            this.price = newProduct.price
            this.title = newProduct.title
            productRepository.save(this)
        }
    }

    fun deleteById(id: String) = productRepository.deleteById(ObjectId(id))

}