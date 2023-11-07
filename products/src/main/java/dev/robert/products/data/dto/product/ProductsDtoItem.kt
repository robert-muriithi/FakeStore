package dev.robert.products.data.dto.product


import com.google.gson.annotations.SerializedName

data class ProductsDtoItem(
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("rating")
    val rating: ProductRating,
    @SerializedName("title")
    val title: String
)