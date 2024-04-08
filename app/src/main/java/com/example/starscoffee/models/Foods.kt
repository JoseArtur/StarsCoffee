package com.example.starscoffee.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Foods(
    val id: Int,
    @SerializedName("name")
    var foodName: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("price")
    var price: Int,
    @SerializedName("image_url")
    var image_url: String,
    var isFavorite: Boolean = false,
    var isAvailable: Boolean = true,
    var quantity: Int = 1
) : Serializable