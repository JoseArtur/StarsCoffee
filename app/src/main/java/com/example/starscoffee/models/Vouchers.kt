package com.evanemran.quickfoods.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

enum class Type {
    PERCENT_OFF, _2_FOR_1, FREE_SIDE
}

val voucherItems = listOf(
    Vouchers(1, "50% off on your order", "50% off on an order of any value", 50, "https://t4.ftcdn.net/jpg/01/16/61/93/360_F_116619399_YA611bKNOW35ffK0OiyuaOcjAgXgKBui.jpg", Type.PERCENT_OFF ),
    Vouchers(2, "2 croissants for the price of 1", "2 croissants for the price for 1 on restaurant x", 0, "https://t4.ftcdn.net/jpg/01/16/61/93/360_F_116619399_YA611bKNOW35ffK0OiyuaOcjAgXgKBui.jpg",Type._2_FOR_1,  ),
    Vouchers(3, "Free chips", "Free cheese on the purchase of any burger", 300, "https://t4.ftcdn.net/jpg/01/16/61/93/360_F_116619399_YA611bKNOW35ffK0OiyuaOcjAgXgKBui.jpg",Type.FREE_SIDE,  ),
)
data class Vouchers (
    val id: Int,
    @SerializedName("name")
    var voucherName: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("value")
    var value: Int,
    @SerializedName("image_url")
    var image_url: String,
    @SerializedName("type")
    var type: Type,
    @SerializedName("restaurant")
    var restaurant: Int = -1,
    var isAvailable: Boolean = true
)