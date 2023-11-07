package dev.robert.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.robert.database.dao.CategoriesDao
import dev.robert.database.dao.ProductDao
import dev.robert.database.database.FakeStoreDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideProductsDao(
        database: FakeStoreDatabase,
    ): ProductDao {
        return database.productDao()
    }

    @Provides
    @Singleton
    fun provideCategoriesDao(
        database: FakeStoreDatabase,
    ): CategoriesDao {
        return database.categoriesDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): FakeStoreDatabase {
        return Room.databaseBuilder(
            context,
            FakeStoreDatabase::class.java,
            "fake_store_database",
        ).build()
    }
}