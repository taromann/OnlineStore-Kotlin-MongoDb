package com.github.assemblathe1.kotlinstore.controllers

import com.github.assemblathe1.kotlinstore.converters.ProductConverter
import com.github.assemblathe1.kotlinstore.dto.ProductDto
import com.github.assemblathe1.kotlinstore.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
class ProductController @Autowired constructor(
    val productService: ProductService,
    val productConverter: ProductConverter
) {

    @GetMapping
    fun findAll(
        @RequestParam(name = "p", defaultValue = "1") page: Int,
        @RequestParam(name = "min_price", required = false) minPrice: Int?,
        @RequestParam(name = "max_price", required = false) maxPrice: Int?,
        @RequestParam(name = "title_part", required = false) titlePart: String?,
        @RequestParam(name = "sortByTitle", required = false) sortByTitle: Boolean?,
        @RequestParam(name = "sortByPrice", required = false) sortByPrice: Boolean?
    ): Page<ProductDto?> {
        // TODO if (page < 1) {page = 1}
        return productService.findAll(page, minPrice, maxPrice, titlePart, sortByTitle, sortByPrice)
            .map { productConverter.entityToDto(it) }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ProductDto =
        productConverter.entityToDto(productService.findById(id))

    @PostMapping
    fun create(@RequestBody productDto: ProductDto): ProductDto? {
        return productConverter.entityToDto(productService.create(productConverter.dtoToEntity(productDto)))
    }

    @PutMapping
    fun update(@RequestBody productDto: ProductDto): ProductDto {
        return productConverter.entityToDto(productService.update(productConverter.dtoToEntity(productDto)))
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String) = productService.deleteById(id)


}