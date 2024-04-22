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
    fun getAllVouchers(): String {
        val url = "http://192.168.56.1:8090/coffee-shop/get-all-vouchers"

        val request = Request.Builder()
            .url(url)
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

    suspend fun callRequest(request: Request): String? {
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
