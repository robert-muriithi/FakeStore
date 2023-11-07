package dev.robert.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.robert.database.converters.ProductEntityConverters
import dev.robert.database.dao.CategoriesDao
import dev.robert.database.dao.ProductDao
import dev.robert.database.entities.CategoryEntity
import dev.robert.database.entities.ProductEntity

@Database(entities = [ProductEntity::class, CategoryEntity::class], version = 1, exportSchema = false)
@TypeConverters(ProductEntityConverters::class)
abstract class FakeStoreDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun categoriesDao(): CategoriesDao
}