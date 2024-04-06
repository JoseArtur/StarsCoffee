package com.evanemran.quickfoods.listeners

interface ClickListener<T> {
    fun onClicked(data: T)
}