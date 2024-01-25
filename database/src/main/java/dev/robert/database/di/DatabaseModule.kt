package dev.robert.database.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.robert.database.converters.ProductEntityConverters
import dev.robert.database.dao.CategoriesDao
import dev.robert.database.dao.ProductDao
import dev.robert.database.database.FakeStoreDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideGson () = Gson()

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
        converters: ProductEntityConverters
    ): FakeStoreDatabase {
        return Room.databaseBuilder(
            context,
            FakeStoreDatabase::class.java,
            "fake_store_database",
        ).addTypeConverter(converters).build()
    }

    @Singleton
    @Provides
    fun provideProductEntityConverters(gson: Gson): ProductEntityConverters {
        return ProductEntityConverters(gson)
    }
}