package com.example.starscoffee.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starscoffee.FoodDetailActivity
import com.example.starscoffee.StarsCoffeeAPI
import com.example.starscoffee.adapters.FoodListAdapter
import com.example.starscoffee.databinding.FragmentFoodBinding
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.Foods
import com.example.starscoffee.models.parseJsonToFoodsList
import com.google.gson.Gson
import kotlinx.coroutines.launch

class FoodListFragment : Fragment() {
    private var _binding: FragmentFoodBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

    private fun getFoodList(): List<Foods> {
        val api = StarsCoffeeAPI()
        val foods = api.getAllFoods()
        return parseJsonToFoodsList(foods)
    }

    private val foodClickListener = object : ClickListener<Foods> {
        override fun onClicked(data: Foods) {
            val gson = Gson()
            val jsonString = gson.toJson(data)
            startActivity(
                Intent(context, FoodDetailActivity::class.java).putExtra(
                    "foods",
                    jsonString
                )
            )

        }
    }
}