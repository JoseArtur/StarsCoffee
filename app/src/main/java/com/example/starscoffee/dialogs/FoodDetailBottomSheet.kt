package com.example.starscoffee.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

import com.example.starscoffee.databinding.BottomsheetFoodDetailBinding

class FoodDetailBottomSheet : BottomSheetDialogFragment() {

        private var _binding: BottomsheetFoodDetailBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
                _binding = BottomsheetFoodDetailBinding.inflate(inflater, container, false)

                binding.buttonAddToCart.setOnClickListener {
                        Toast.makeText(activity, "Added to cart!", Toast.LENGTH_SHORT).show()
                        dismiss()
                }

                return binding.root
        }

        override fun onDestroyView() {
                super.onDestroyView()
                _binding = null
        }
}