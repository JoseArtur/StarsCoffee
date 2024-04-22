package com.example.starscoffee.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FoodsResponse(
    val foods: List<Foods>
)

data class Foods(
    val id: Int,
    var foodName: String,
    var description: String,
    var price: Double,
    var itemType: String,
    @SerializedName("image_url")
    var imageUrl: String,
    var isFavorite: Int = 0,
    var isAvailable: Int = 1,
    var quantity: Int = 1,
    var customizationOptions: String,
    var specialAttributes: String
) : Serializable

fun parseJsonToFoodsList(jsonString: String): List<Foods> {
    val gson = Gson()
    val foodsResponse = gson.fromJson(jsonString, FoodsResponse::class.java)
    return foodsResponse.foods
}