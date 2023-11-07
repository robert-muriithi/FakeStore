package dev.robert.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.robert.database.entities.ProductEntity
@Dao
interface ProductDao {
    @Query(value = "SELECT * FROM product")
    suspend fun getAllProducts(): List<ProductEntity>

    @Query(value = "SELECT * FROM product WHERE id = :id")
    suspend fun getProductById(id: Int): ProductEntity

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Query(value = "DELETE FROM product")
    suspend fun deleteAllProducts()

    @Insert
    suspend fun insertProduct(product: ProductEntity)
}