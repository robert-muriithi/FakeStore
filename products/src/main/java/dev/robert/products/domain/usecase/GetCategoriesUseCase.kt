package dev.robert.products.domain.usecase

import dev.robert.products.data.utils.Resource
import dev.robert.products.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase (private val repository: ProductsRepository) {
    suspend operator fun invoke() : Flow<Resource<List<String>>> {
        return repository.getCategories()
    }
}