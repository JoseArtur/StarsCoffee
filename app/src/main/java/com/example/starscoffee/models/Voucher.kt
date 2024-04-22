package com.evanemran.quickfoods.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VouchersResponse(
    val vouchers: List<Voucher>
)
data class Voucher (
    val id: Int,
    val pointsRequired: Int,
    @SerializedName("name")
    var voucherName: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("value")
    var value: Int,
    @SerializedName("image_url")
    var image_url: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("restaurant")
    var restaurant: Int = -1,
    var isAvailable: Boolean = true
) : Serializable

fun parseJsonToVouchersList(jsonString: String): List<Voucher> {
    val gson = Gson()
    val vouchersResponse = gson.fromJson(jsonString, VouchersResponse::class.java)
    return vouchersResponse.vouchers
}


