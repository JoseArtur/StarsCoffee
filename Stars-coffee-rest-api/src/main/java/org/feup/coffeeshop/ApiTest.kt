package org.feup.coffeeshop

import okhttp3.*
import org.junit.jupiter.api.Test
import java.io.IOException

class ApiTest {

    @Test
    fun testRun() {
        val url = "http://localhost:8090/coffee-shop/get-all-customers"
        val response = run(url)
        if (response.isSuccessful) {
            val responseBody = response.body?.string()
            println("Response: $responseBody")
            assert(responseBody != null)
        } else {
            println("Unexpected response code: ${response.code}")
            assert(false)
        }
    }

    private fun run(url: String): Response {
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(url)
                .get() // Liberato if you need to do a POST request update this line to .post()
                .build()

        return try {
            client.newCall(request).execute()
        } catch (e: IOException) {
            println("Failed to execute request: ${e.message}")
            throw e
        }
    }
}
