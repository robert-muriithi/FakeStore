package dev.robert.products.data.datasource.remote

import dev.robert.products.data.dto.product.AddProductRequestDto
import dev.robert.products.data.dto.product.ProductsDto
import dev.robert.products.data.dto.product.ProductsDtoItem
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsApi {
    @GET("products")
    suspend fun getProducts(): List<ProductsDtoItem>

    @GET("products/{id}")
    suspend fun getProduct(id: Int): ProductsDtoItem

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(category: String): List<ProductsDtoItem>

    @POST("products")
    suspend fun addProduct(product: AddProductRequestDto): AddProductRequestDto

    @GET("products/categories")
    suspend fun getCategories(): List<String>
}