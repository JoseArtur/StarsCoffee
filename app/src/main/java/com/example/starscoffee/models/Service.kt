package com.example.starscoffee.models

import com.example.starscoffee.R


enum class Service(title: String, subtitle: String, icon: Int) {

    ITEMS("Food Order","Order your food here", R.drawable.shops);

    var title: String = title
    var subtitle: String = subtitle
    var icon: Int = icon

}