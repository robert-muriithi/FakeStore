package dev.robert.products.data.dto.product


import com.google.gson.annotations.SerializedName

data class ProductRating(
    @SerializedName("count")
    val count: Int,
    @SerializedName("rate")
    val rate: Double
)