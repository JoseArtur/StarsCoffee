package com.evanemran.quickfoods.models

class AddOns(id: Int, title: String, price: Int, isChecked: Boolean) {
    var addOnId: Int = id
    var addOnPrice: Int = price
    var addOnName: String = title
    var addOnChecked: Boolean = isChecked
}