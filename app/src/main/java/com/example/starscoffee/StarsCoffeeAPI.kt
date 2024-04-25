package com.example.starscoffee

import okhttp3.*
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class StarsCoffeeAPI {
    var resp : String = ""

    fun getAllVouchers(userId: String): String {
        val httpUrl = HttpUrl.Builder()
            .scheme("http")
            .host("10.0.2.2")
            .port(8090)
            .addPathSegment("coffee-shop")
            .addPathSegment("get-all-vouchers")
            .addQueryParameter("userId", userId)
            .build()

        val request = Request.Builder()
            .url(httpUrl)
            .build()

        var v2 = ""
        val vouchersDeferred = GlobalScope.async(Dispatchers.IO) {
            callRequest(request)
        }

        runBlocking {
            // Wait for the result and access the list of vouchers
            val vouchers = vouchersDeferred.await()
            v2 = vouchers.toString()
            // Process the list of vouchers as needed
            println("Vouchers1: $vouchers")
        }
        return v2
    }

    fun getAllFoods(): String {
        val url = "http://10.0.2.2:8090/coffee-shop/get-all-foods"

        val request = Request.Builder()
            .url(url)
            .build()

        var foodsString = ""
        val foodsDeferred = GlobalScope.async(Dispatchers.IO) {
            callRequest(request)
        }

        runBlocking {
            // Wait for the result and access the list of vouchers
            val foods = foodsDeferred.await()
            foodsString = foods.toString()
            // Process the list of vouchers as needed
            println("Foods: $foods")
        }
        return foodsString
    }

    suspend fun callRequest(request: Request): String? {
        val client = OkHttpClient()
        return try {
            val response: Response = client.newCall(request).execute()
            println(response)
            if (response.isSuccessful) {
                response.body()?.string()
            } else {
                println("Unexpected response code: ${response.code()}")
                null
            }
        } catch (e: IOException) {
            println("Exception during network call: ${e.message}")
            null
        }
    }
}
