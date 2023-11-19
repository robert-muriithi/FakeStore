package dev.robert.products.data.repository

import dev.robert.database.dao.CategoriesDao
import dev.robert.database.dao.ProductDao
import dev.robert.database.entities.CategoryEntity
import dev.robert.products.data.datasource.remote.ProductsApi
import dev.robert.products.data.mappers.toDomain
import dev.robert.products.data.mappers.toEntity
import dev.robert.products.data.utils.Resource
import dev.robert.products.domain.model.Product
import dev.robert.products.domain.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProductsRepositoryImpl (
    private val productsApi: ProductsApi,
    private val productsDao: ProductDao,
    private val categoryDao: CategoriesDao
) : ProductsRepository {
    override suspend fun getProducts(): Flow<Resource<List<Product>>> = flow {
       val cachedProducts = productsDao.getAllProducts()
        if (cachedProducts.isNotEmpty()) {
            emit(Resource.Success(cachedProducts.map { it.toDomain() }))
        } else {
            try{
                val products = productsApi.getProducts()
                val product = products.map { it.toEntity() }
                productsDao.deleteAllProducts()
                productsDao.insertProducts(product)
                val cachedProducts = productsDao.getAllProducts()
                emit(Resource.Success(cachedProducts.map { it.toDomain() }))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getProduct(id: Int): Flow<Resource<Product>> = flow {
        val cachedProduct = productsDao.getProductById(id)
        emit(Resource.Success(cachedProduct.toDomain()))
    }.flowOn(Dispatchers.IO)


    override suspend fun getProductsByCategory(category: String): Flow<Resource<List<Product>>> = flow {
        val cachedProducts = productsDao.getAllProducts()
        if (cachedProducts.isNotEmpty()) {
            emit(Resource.Success(cachedProducts.map { it.toDomain() }))
        } else {
            try{
                val products = productsApi.getProductsByCategory(category)
                val productsDto = products.map { it.toEntity() }
                productsDao.deleteAllProducts()
                productsDao.insertProducts(productsDto)
                val cachedProducts = productsDao.getAllProducts()
                emit(Resource.Success(cachedProducts.map { it.toDomain() }))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getCategories(): Flow<Resource<List<String>>> = flow {
        val cachedCategories = categoryDao.getAllCategories()
        val categories = cachedCategories.map { it.category }
        if (cachedCategories.isNullOrEmpty()){
            try {
                val remoteData = productsApi.getCategories()
                categoryDao.insertCategories(remoteData.map {
                    CategoryEntity(category = it)
                })
                val cachedCategories = categoryDao.getAllCategories()
                val categories = cachedCategories.map { it.category }
                emit(Resource.Success(categories))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        } else {
            emit(Resource.Success(categories))
        }

    }.flowOn(Dispatchers.IO)

}


