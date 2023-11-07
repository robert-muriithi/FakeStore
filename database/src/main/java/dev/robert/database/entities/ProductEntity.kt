package dev.robert.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    val category: String,
    val description: String,
    @PrimaryKey val id: Int,
    val image: String,
    val price: Double,
    val rating: ProductRating,
    val title: String
)
data class ProductRating(
    val count: Int,
    val rate: Double
)

