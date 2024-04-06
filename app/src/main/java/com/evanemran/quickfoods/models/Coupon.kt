package com.evanemran.quickfoods.models

class Coupon(id: Int, code: String, title: String, amount: Int, validity: String, isValid: Boolean) {

    var couponId: Int = id
    var couponCode: String = code
    var couponTitle: String = title
    var couponAmt: Int = amount
    var couponValidity: String = validity
    var couponIsValid: Boolean = isValid
}