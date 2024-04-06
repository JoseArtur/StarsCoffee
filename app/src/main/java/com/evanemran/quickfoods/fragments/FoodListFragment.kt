package com.evanemran.quickfoods.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evanemran.quickfoods.CoffeeAPIService
import com.evanemran.quickfoods.FoodDetailActivity
import com.evanemran.quickfoods.R
import com.evanemran.quickfoods.adapters.FoodListAdapter
import com.evanemran.quickfoods.dialogs.FoodDetailBottomSheet
import com.evanemran.quickfoods.listeners.ClickListener
import com.evanemran.quickfoods.models.Foods
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodListFragment : Fragment() {
private var view: View? = null

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
                view = inflater.inflate(R.layout.fragment_food, container, false)

                val recyclerFoods = view?.findViewById<RecyclerView>(R.id.recycler_foods)

                lifecycleScope.launch {
                        val foodList = getFoodList()

                        recyclerFoods?.apply {
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                                adapter = FoodListAdapter(context, foodList, foodClickListener)
                        }
                }

                return view
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
        }