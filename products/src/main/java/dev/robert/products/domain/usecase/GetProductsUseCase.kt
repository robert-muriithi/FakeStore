package dev.robert.products.domain.usecase

import dev.robert.products.domain.repository.ProductsRepository

class GetProductsUseCase(private val repository: ProductsRepository) {
        suspend operator fun invoke() = repository.getProducts()
}