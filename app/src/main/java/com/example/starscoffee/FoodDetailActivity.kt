package com.example.starscoffee

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.starscoffee.databinding.ActivityFoodDetailBinding
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.AddOns
import com.example.starscoffee.models.Foods

class FoodDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodData = intent.getSerializableExtra("food") as Foods
        val textViewFoodName = findViewById<TextView>(R.id.textView_foodName)
        textViewFoodName.text = foodData.foodName
        // set the "@+id/textView_foodPrice" also
        val textViewFoodPrice = findViewById<TextView>(R.id.textView_foodPrice)
        textViewFoodPrice.text = foodData.price.toString()

        // set the android:id="@+id/textView_foodInfo" also
        val textViewFoodInfo = findViewById<TextView>(R.id.textView_foodInfo)
        textViewFoodInfo.text = foodData.description
        //    setAddons()
        val textViewCartCount = findViewById<TextView>(R.id.textView_cartCount)


        foodData.quantity = 1
        // Get a reference to the "+" button and set an OnClickListener
        val buttonIncrease = findViewById<ImageButton>(R.id.button_increase)
        buttonIncrease.setOnClickListener {
            val currentCount = textViewCartCount.text.toString().toInt()
            textViewCartCount.text = (currentCount + 1).toString()
            foodData.quantity = currentCount + 1
        }
        // Get a reference to the "-" button and set an OnClickListener
        val buttonDecrease = findViewById<ImageButton>(R.id.button_decrease)
        buttonDecrease.setOnClickListener {
            val currentCount = textViewCartCount.text.toString().toInt()
            if (currentCount > 1) { // Only decrease the count if it's greater than 1
                textViewCartCount.text = (currentCount - 1).toString()
                foodData.quantity = currentCount - 1
            }
        }

        binding.buttonAddToCart.setOnClickListener {
            if (foodData.quantity < 1) {
                Toast.makeText(this, "Quantity must be at least 1", Toast.LENGTH_SHORT).show()
            } else {
                val existingItem = CartActivity.cartList.find { it.id == foodData.id }
                if (existingItem != null) {
                    existingItem.quantity += foodData.quantity
                } else {
                    CartActivity.cartList.add(foodData)
                }
                Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show()
                this.finish()
            }
        }
    }

    private val addOnClickListener: ClickListener<AddOns> = object :
        ClickListener<AddOns> {
        override fun onClicked(data: AddOns) {
            startActivity(Intent(this@FoodDetailActivity, RestaurantDetailActivity::class.java))
        }

    }
}