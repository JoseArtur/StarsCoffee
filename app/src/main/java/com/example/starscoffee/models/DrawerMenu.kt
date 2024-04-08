package com.example.starscoffee.models

import com.example.starscoffee.R


enum class DrawerMenu(title: String, icon: Int) {

    FAVORITES("Favorites", R.drawable.ic_love),
    ORDER_REORDER("Order & Reorder", R.drawable.ic_order),
    VOUCHERS("Vouchers", R.drawable.ic_voucher),
    LOGOUT("Log-out", R.drawable.ic_logout);

    var title: String = title
    var icon: Int = icon

}