package com.evanemran.quickfoods.models

import com.evanemran.quickfoods.R

enum class DrawerMenu(title: String, icon: Int) {

    FAVORITES("Favorites", R.drawable.ic_love),
    ORDER_REORDER("Order & Reorder",R.drawable.ic_order),
    PROFILE("Profile", R.drawable.ic_profile),
    ADDRESS("Addresses", R.drawable.ic_address),
    REWARDS("Challenges & Rewards", R.drawable.ic_rewards),
    VOUCHERS("Vouchers", R.drawable.ic_voucher),
    HELP_CENTER("Help center", R.drawable.ic_help),
    SETTINGS("Settings", R.drawable.ic_settings),
    TERMS("Privacy & Terms", R.drawable.ic_terms),
    LOGOUT("Log-out", R.drawable.ic_logout);

    var title: String = title
    var icon: Int = icon

}