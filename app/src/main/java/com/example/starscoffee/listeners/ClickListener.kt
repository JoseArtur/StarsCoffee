package com.example.starscoffee.listeners

interface ClickListener<T> {
    fun onClicked(data: T)
}