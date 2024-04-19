package com.example.starscoffee

import com.evanemran.quickfoods.models.Voucher
import okhttp3.*
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.evanemran.quickfoods.models.parseJsonToVouchersList
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class StarsCoffeeAPI {
    var resp : String = ""
    fun getAllVouchers(): String {
        val url = "http://192.168.56.1:8090/coffee-shop/get-all-vouchers"

        val request = Request.Builder()
            .url(url)
            .build()

        var v2 = ""
        val vouchersDeferred = GlobalScope.async(Dispatchers.IO) {
            callRequest4(request)
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

    fun callRequest(request : Request) {
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request: ${e.message}")
                // Handle failure here
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    println("Unexpected response code: ${response.code()}")
                    // Handle unsuccessful response here
                    return
                }

                val responseBody = response.body()?.string()
                println("Response: $responseBody")
                if (responseBody != null) {
                    resp = responseBody
                }
                // Process response here
            }
        })
    }

    fun callRequest2(request: Request): String? {
        val client = OkHttpClient()
        val response: Response = client.newCall(request).execute()
        return if (response.isSuccessful) {
            response.body()?.string()
        } else {
            println("Unexpected response code: ${response.code()}")
            null
        }
    }

    suspend fun callRequest3(request: Request): String? {
        val client = OkHttpClient()
        return try {
            val response: Response = client.newCall(request).execute()
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

    suspend fun callRequest4(request: Request): String? {
        val client = OkHttpClient()
        return try {
            val response: Response = client.newCall(request).execute()
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
