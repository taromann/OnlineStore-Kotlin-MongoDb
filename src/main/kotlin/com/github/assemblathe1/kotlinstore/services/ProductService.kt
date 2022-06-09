package com.github.assemblathe1.kotlinstore.services

import com.github.assemblathe1.kotlinstore.entities.Product
import com.github.assemblathe1.kotlinstore.repositories.ProductRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService @Autowired constructor(
    val mongoTemplate: MongoTemplate,
    val productRepository: ProductRepository
) {

    fun getAll(minPrice: Int?, maxPrice: Int?, titlePart: String?, page: Int): Page<Product?> /*List<Product>*/ {
        val pageable = PageRequest.of(page - 1, 5)
        val query = Query()
        query.with(pageable)

        titlePart?.let { query.addCriteria(Criteria.where("title").regex(titlePart)) }
        val priceCriteria = Criteria("price")
        minPrice?.let { priceCriteria.gte(minPrice) } ?: priceCriteria.gte(0)
        maxPrice?.let { priceCriteria.lte(maxPrice) } ?: priceCriteria.lte(Int.MAX_VALUE)
        query.addCriteria(priceCriteria)

        return PageableExecutionUtils.getPage(
            mongoTemplate.find(query, Product::class.java),
            pageable
        ) { mongoTemplate.count(query, Product::class.java) }
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