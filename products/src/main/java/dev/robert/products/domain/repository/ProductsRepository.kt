package dev.robert.products.domain.repository

import dev.robert.products.data.utils.Resource
import dev.robert.products.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(): Flow<Resource<List<Product>>>

    suspend fun getProduct(id: Int): Flow<Resource<Product>>

    suspend fun getProductsByCategory(category: String): Flow<Resource<List<Product>>>

    suspend fun getCategories(): Flow<Resource<List<String>>>
}