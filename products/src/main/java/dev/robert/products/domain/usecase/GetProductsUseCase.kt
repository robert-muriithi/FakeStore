package dev.robert.products.domain.usecase

import dev.robert.products.data.utils.Resource
import dev.robert.products.domain.model.Product
import dev.robert.products.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(private val repository: ProductsRepository) {
        suspend operator fun invoke() : Flow<Resource<List<Product>>> {
            return repository.getProducts()
        }
}