package com.example.starscoffee

import com.example.starscoffee.models.Foods
import retrofit2.Call
import retrofit2.http.GET

interface CoffeeAPIService {
    @GET("menu_items.json")
    fun getMenuItems(): Call<List<Foods>>
}