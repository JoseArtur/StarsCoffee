package com.example.starscoffee.models

class AddOns(id: Int, title: String, price: Double, isChecked: Boolean) {
    var addOnId: Int = id
    var addOnPrice: Double = price
    var addOnName: String = title
    var addOnChecked: Boolean = isChecked
}