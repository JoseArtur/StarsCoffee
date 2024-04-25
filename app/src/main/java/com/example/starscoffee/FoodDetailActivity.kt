package com.example.starscoffee

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.starscoffee.databinding.ActivityFoodDetailBinding
import com.example.starscoffee.models.Foods
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class FoodDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val foodsString = intent.getStringExtra("foods")
        val gson = Gson()
        val food: Foods = gson.fromJson(foodsString, Foods::class.java)
        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textViewFoodName = findViewById<TextView>(R.id.textView_foodName)
        textViewFoodName.text = food.foodName

        val imageViewFoodImage = findViewById<ImageView>(R.id.imageView_foodImage)
        Picasso.get().load(food.imageUrl).into(imageViewFoodImage)

        // set the "@+id/textView_foodPrice" also
        val textViewFoodPrice = findViewById<TextView>(R.id.textView_foodPrice)
        textViewFoodPrice.text = food.price.toString()

        // set the android:id="@+id/textView_foodInfo" also
        val textViewFoodInfo = findViewById<TextView>(R.id.textView_foodInfo)
        textViewFoodInfo.text = food.description
        //   setAddons()
        val textViewCartCount = findViewById<TextView>(R.id.textView_cartCount)


        food.quantity = 1
        // Get a reference to the "+" button and set an OnClickListener
        val buttonIncrease = findViewById<ImageButton>(R.id.button_increase)
        buttonIncrease.setOnClickListener {
            val currentCount = textViewCartCount.text.toString().toInt()
            textViewCartCount.text = (currentCount + 1).toString()
            food.quantity = currentCount + 1
        }
        // Get a reference to the "-" button and set an OnClickListener
        val buttonDecrease = findViewById<ImageButton>(R.id.button_decrease)
        buttonDecrease.setOnClickListener {
            val currentCount = textViewCartCount.text.toString().toInt()
            if (currentCount > 1) { // Only decrease the count if it's greater than 1
                textViewCartCount.text = (currentCount - 1).toString()
                food.quantity = currentCount - 1
            }
        }

        binding.buttonAddToCart.setOnClickListener {
            if (food.quantity < 1) {
                Toast.makeText(this, "Quantity must be at least 1", Toast.LENGTH_SHORT).show()
            } else {
                val existingItem = CartActivity.cartList.find { it.id == food.id }
                if (existingItem != null) {
                    existingItem.quantity += food.quantity
                } else {
                    CartActivity.cartList.add(food)
                }
                Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show()
                this.finish()
            }
        }


    }

}