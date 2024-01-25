package dev.robert.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.robert.database.entities.ProductEntity
import dev.robert.database.entities.ProductRating

@ProvidedTypeConverter
class ProductEntityConverters(
    private val gson : Gson
) {

    @TypeConverter
    fun fromProductEntityList(productEntityList: List<ProductEntity>): String {
        val type = object : TypeToken<List<ProductEntity>>() {}.type
        return gson.toJson(productEntityList, type)
    }

    @TypeConverter
    fun toProductEntityList(productEntityListString: String): List<ProductEntity> {
        val type = object : TypeToken<List<ProductEntity>>() {}.type
        return gson.fromJson(productEntityListString, type)
    }

    @TypeConverter
    fun fromProductRating(productRating: ProductRating): String {
        val type = object : TypeToken<ProductRating>() {}.type
        return gson.toJson(productRating, type)
    }

    @TypeConverter
    fun toProductRating(productRatingString: String): ProductRating {
        val type = object : TypeToken<ProductRating>() {}.type
        return gson.fromJson(productRatingString, type)
    }
}