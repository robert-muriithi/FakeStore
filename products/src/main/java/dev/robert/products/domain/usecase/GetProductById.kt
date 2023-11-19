package dev.robert.products.domain.usecase

import dev.robert.products.domain.repository.ProductsRepository

class GetProductById(private val repository: ProductsRepository) {
        suspend operator fun invoke(id: Int) = repository.getProduct(id)
}