package com.github.assemblathe1.kotlinstore.controllers
import ccom.github.assemblathe1.kotlinstore.dto.ProductDto
import com.github.assemblathe1.kotlinstore.converters.ProductConverter
import com.github.assemblathe1.kotlinstore.services.ProductService
import com.github.assemblathe1.kotlinstore.validators.ProductValidator


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
class ProductsController @Autowired constructor(
    val productService: ProductService,
    val productConverter: ProductConverter,
    val productValidator: ProductValidator
) {

    @GetMapping
    fun findAll(
        @RequestParam(name = "p", defaultValue = "1") page: Int,
        @RequestParam(name = "min_price", required = false) minPrice: Int?,
        @RequestParam(name = "max_price", required = false) maxPrice: Int?,
        @RequestParam(name = "title_part", required = false) titlePart: String?,
        @RequestParam(name = "sortByTitle", defaultValue = "1", required = false) sortByTitle: Boolean?,
        @RequestParam(name = "sortByPrice", required = false) sortByPrice: Boolean?
    ): Page<ProductDto?> {
        println("${page}, ${minPrice}, ${maxPrice}, ${titlePart}, ${sortByTitle}, ${sortByPrice}")
        // TODO if (page < 1) {page = 1}
        return productService.findAll(page, minPrice, maxPrice, titlePart, sortByTitle, sortByPrice)
            .map { productConverter.entityToDto(it) }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ProductDto {
        productValidator.validateIdDto(id)
        return productService.findById(id).let { productConverter.entityToDto(it) }
    }


    @PostMapping
    fun create(@RequestBody productDto: ProductDto): ProductDto? {
        productValidator.validateTitleAndPriceDto(productDto)
        return productConverter.entityToDto(productService.create(productConverter.dtoToEntity(productDto)))
    }

    @PutMapping
    fun update(@RequestBody productDto: ProductDto): ProductDto? {
        productValidator.validateAllProductDto(productDto)
        return productService.update(productConverter.dtoToEntity(productDto))?.let { productConverter.entityToDto(it) }
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String) = productService.deleteById(id)


}