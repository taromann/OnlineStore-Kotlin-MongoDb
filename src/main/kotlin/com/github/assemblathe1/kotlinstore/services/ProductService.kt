package com.github.assemblathe1.kotlinstore.services

import com.geekbrains.spring.web.entities.Product
import com.github.assemblathe1.kotlinstore.exceptions.exceptions.ResourceNotFoundException
import com.github.assemblathe1.kotlinstore.repositories.ProductRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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

    fun findAll(
        page: Int,
        minPrice: Int?,
        maxPrice: Int?,
        titlePart: String?,
        sortByTitle: Boolean?,
        sortByPrice: Boolean?
    ): Page<Product> {
        val pageable = PageRequest.of(page - 1, 10)
        val query = Query()
        query.with(pageable)

        val priceCriteria = Criteria("price")
        minPrice?.let { priceCriteria.gte(minPrice) } ?: priceCriteria.gte(0)
        maxPrice?.let { priceCriteria.lte(maxPrice) } ?: priceCriteria.lte(Int.MAX_VALUE)
        titlePart?.let { query.addCriteria(Criteria.where("title").regex(titlePart)) }
        sortByTitle?.let {
            query.with(
                Sort.by(getSortingDirection(sortByTitle), "title").and(Sort.by(Sort.Direction.ASC, "price"))
            )
        }
        sortByPrice?.let {
            query.with(Sort.by(getSortingDirection(sortByPrice), "price"))
        }
        query.addCriteria(priceCriteria)

        return PageableExecutionUtils.getPage(
            mongoTemplate.find(query, Product::class.java), pageable
        ) { mongoTemplate.count(query, Product::class.java) }
    }

    fun create(product: Product): Product = productRepository.insert(product)

    fun findById(id: String): Product? =
        productRepository.findByIdOrNull(ObjectId(id)) ?: throw ResourceNotFoundException("Product was not found")

    @Transactional
    fun update(newProduct: Product): Product? {
        return newProduct.id?.let {
            println(it.toHexString())
            findById(it.toHexString())?.apply {
                this.price = newProduct.price
                this.title = newProduct.title
                productRepository.save(this)
            }
        }
    }

    fun deleteById(id: String) = productRepository.deleteById(ObjectId(id))

    private fun getSortingDirection(pathVariable: Boolean) =
        if (pathVariable) Sort.Direction.ASC else Sort.Direction.DESC

}