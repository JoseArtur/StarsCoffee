import okhttp3.*
import java.io.IOException

class ApiTest {
    fun testRun() {
        val url = "http://192.168.56.1:8090/coffee-shop/get-all-customers"
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()

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
                // Process response here
            }
        })
    }
}
