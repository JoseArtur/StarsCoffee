package com.example.starscoffee.models

import java.io.Serializable

class Address(
    var id: Int,
    var name: String,
    var type: String,
    var lat: Double,
    var lng: Double,
    var isPrimary: Boolean
) : Serializable {
    var addressId: Int = id
    var addressName: String = name
    var addressType: String = type
    var addressLat: Double = lat
    var addressLng: Double = lng
    var isPrimaryAddress: Boolean = isPrimary
}