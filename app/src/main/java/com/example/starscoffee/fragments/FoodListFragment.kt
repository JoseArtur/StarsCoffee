package com.example.starscoffee.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starscoffee.CoffeeAPIService
import com.example.starscoffee.FoodDetailActivity
import com.example.starscoffee.adapters.FoodListAdapter
import com.example.starscoffee.dialogs.FoodDetailBottomSheet
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.Foods
import com.example.starscoffee.databinding.FragmentFoodBinding
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodListFragment : Fragment() {
private var view: View? = null
        private var _binding: FragmentFoodBinding? = null

        private val binding get() = _binding!!

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
                _binding = FragmentFoodBinding.inflate(inflater, container, false)

                lifecycleScope.launch {
                        val foodList = getFoodList()

                        binding.recyclerFoods.apply {
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                                adapter = FoodListAdapter(context, foodList, foodClickListener)
                        }
                }

                return binding.root
        }

        private var apiService : CoffeeAPIService
        init {
                val gson = GsonBuilder()
                        .setLenient()
                        .create()
                val retrofit: Retrofit = Retrofit.Builder()
                        .baseUrl("https://stars-coffee-default-rtdb.europe-west1.firebasedatabase.app/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
                apiService = retrofit.create(CoffeeAPIService::class.java)
        }

        private suspend fun getFoodList(): List<Foods> {
                return withContext(Dispatchers.IO) {
                        val response = apiService.getMenuItems().execute()
                        if (response.isSuccessful) {
                                response.body() ?: emptyList()
                        } else {
                                // Handle the error here
                                emptyList()
                        }
                }
        }
private val foodClickListener = object : ClickListener<Foods> {
        override fun onClicked(data: Foods) {
        if (data.foodName == "Set meal 1") {
        val bottomSheet = FoodDetailBottomSheet()
        bottomSheet.show(childFragmentManager, "BottomSheet")
        } else {
        startActivity(Intent(context, FoodDetailActivity::class.java).putExtra("food", data.toString()))
        }
        }
        }
        override fun onDestroyView() {
                super.onDestroyView()
                _binding = null
        }
        }