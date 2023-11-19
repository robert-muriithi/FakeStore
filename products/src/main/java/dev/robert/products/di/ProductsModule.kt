package dev.robert.products.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.robert.database.dao.CategoriesDao
import dev.robert.database.dao.ProductDao
import dev.robert.products.data.datasource.remote.ProductsApi
import dev.robert.products.data.repository.ProductsRepositoryImpl
import dev.robert.products.domain.repository.ProductsRepository
import dev.robert.products.domain.usecase.GetCategoriesUseCase
import dev.robert.products.domain.usecase.GetProductById
import dev.robert.products.domain.usecase.GetProductCategory
import dev.robert.products.domain.usecase.GetProductsUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductsModule {

    @Provides
    @Singleton
    fun provideProductRepository(
        productApi: ProductsApi,
        productDao: ProductDao,
        categoryDao: CategoriesDao
    ): ProductsRepository {
        return ProductsRepositoryImpl(productApi, productDao, categoryDao)
    }


    @Provides
    @Singleton
    fun provideProductApi(
        retrofit: Retrofit
    ): ProductsApi {
        return retrofit.create(ProductsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGetProductsUseCase(
        repository: ProductsRepository
    ): GetProductsUseCase {
        return GetProductsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetProductByIdUseCase(
        repository: ProductsRepository
    ): GetProductById {
        return GetProductById(repository)
    }
    @Provides
    @Singleton
    fun provideGetProductByCategoryUseCase(
        repository: ProductsRepository
    ): GetProductCategory {
        return GetProductCategory(repository)
    }

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(
        repository: ProductsRepository
    ): GetCategoriesUseCase {
        return GetCategoriesUseCase(repository)
    }


}