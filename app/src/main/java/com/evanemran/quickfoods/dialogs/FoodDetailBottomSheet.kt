package com.evanemran.quickfoods.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.evanemran.quickfoods.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FoodDetailBottomSheet : BottomSheetDialogFragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bottomsheet_food_detail, container, false)

        val addCart: Button = view.findViewById(R.id.button_addToCart)
        addCart.setOnClickListener {
        Toast.makeText(activity, "Added to cart!", Toast.LENGTH_SHORT).show()
        dismiss()
        }

        return view
        }
        }