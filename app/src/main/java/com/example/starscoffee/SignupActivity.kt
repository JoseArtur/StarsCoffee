package com.example.starscoffee

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.starscoffee.databinding.ActivitySignupBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
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
            val password = binding.editTextPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, " enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            login(email, password)
        }
    }
    private fun login(email: String, password: String) {
        val url = "http://10.227.158.196:8090/coffee-shop/login" // bella url
        val requestBody = FormBody.Builder()
            .add("email", email)
            .add("password", password)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body()?.string()
                    // Aqui você pode usar responseBody como desejar, por exemplo, para extrair informações de usuário
                    // e iniciar a atividade principal
                    val intent = Intent(this@SignupActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    runOnUiThread {
                        Toast.makeText(this@SignupActivity, "Login falhou", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {

                runOnUiThread {
                    Toast.makeText(this@SignupActivity, "Erro de rede: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}