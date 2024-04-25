package com.example.starscoffee

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.starscoffee.databinding.ActivitySignupBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignup.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val nif = binding.editTextNif.text.toString()
            val telephone = binding.editTextNumber.text.toString()
            val confirmPassword = binding.editTextConfirmPassword.text.toString()
            val name = binding.editTextName.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            register(email, name, nif, telephone, password)
        }
        binding.textViewLogin.setOnClickListener {
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(email: String, password: String) {
        val url = "http://10.0.2.2:8090/coffee-shop/login"
        val json = "{\"email\":\"$email\",\"password\":\"$password\"}"
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
                    val intent = Intent(this@SignupActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    runOnUiThread {
                        Toast.makeText(this@SignupActivity, "Login failed", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(
                        this@SignupActivity,
                        "Network error: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun register(
        email: String,
        name: String,
        nif: String,
        telephone: String,
        password: String
    ) {
        val url = "http://10.0.2.2:8090/coffee-shop/register"
        val json =
            "{\"email\":\"$email\", \"name\":\"$name\", \"nif\":\"$nif\", \"password\":\"$password\", \"telephone\":\"$telephone\", \"points\":\"0\"}"
        val requestBody = RequestBody.create(MediaType.parse("application/json"), json)

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .addHeader("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                println("test error register message: ")
                println(response.code())
                if (response.isSuccessful) {

                    val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()

                    editor.putString("name", name)
                    editor.putString("nif", nif)
                    editor.putString("telephone", telephone)
                    editor.putString("email", email)
                    editor.putInt("points", 0)
                    editor.apply()


                    val intent = Intent(this@SignupActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    runOnUiThread {
                        Toast.makeText(this@SignupActivity, "Register failed", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(
                        this@SignupActivity,
                        "Network error: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

}