package dev.robert.products.domain.usecase

import dev.robert.products.domain.repository.ProductsRepository

class GetProductCategory(private val repository: ProductsRepository) {
        suspend operator fun invoke(category: String) = repository.getProductsByCategory(category)
}