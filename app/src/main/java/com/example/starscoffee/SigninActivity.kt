package com.example.starscoffee

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.starscoffee.databinding.ActivitySigninBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class SigninActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignin.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            login(email, password)
        }
        binding.textViewSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
    private fun login(email: String, password: String) {
        val url = "http://172.24.155.55:8090/coffee-shop/login"
        val json = "{\"email\":\"$email\", \"password\":\"$password\"}"
        val requestBody = RequestBody.create(MediaType.parse("application/json"), json)

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .addHeader("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                println("test error login message: ")
                println(response.code())
                if (response.isSuccessful) {
// Call /customer-by-email/{email} to get the user data
                    val customerUrl = "http://172.24.155.55:8090/coffee-shop/customer-by-email/$email"
                    val customerRequest = Request.Builder()
                        .url(customerUrl)
                        .build()

                    client.newCall(customerRequest).enqueue(object : Callback {
                        override fun onResponse(call: Call, response: Response) {
                            if (response.isSuccessful) {
                                // Parse the JSON response to get the user info
                                val userInfoJson = response.body()?.string()
                                val jsonObject = JSONObject(userInfoJson)
                                val customersArray = jsonObject.getJSONArray("customers")
                                val userInfo = customersArray.getJSONObject(0)

                                val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

                                val editor = sharedPreferences.edit()
                                editor.putString("name", userInfo.getString("name"))
                                editor.putString("nif", userInfo.getString("nif"))
                                editor.putString("telephone", userInfo.getString("telephone"))
                                editor.putString("email", userInfo.getString("email"))
                                editor.putInt("points", userInfo.getInt("points"))
                                editor.apply()

                                val intent = Intent(this@SigninActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                runOnUiThread {
                                    Toast.makeText(this@SigninActivity, "Failed to get user data", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                        override fun onFailure(call: Call, e: IOException) {
                            runOnUiThread {
                                Toast.makeText(this@SigninActivity, "Network error: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                } else {
                    runOnUiThread {
                        Toast.makeText(this@SigninActivity, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@SigninActivity, "Network error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }}