package com.evanemran.quickfoods.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
data class Users(
    val id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("nif")
    var nif: Int,
    @SerializedName("password")
    var password: String,
    @SerializedName("points")
    var points: Int,
    @SerializedName("surname")
    var surname: String,
    @SerializedName("username")
    var username: String
)