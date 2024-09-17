package com.lhb.kiotviet_quanly.model

data class Product(
    val id: String = "",
    val name: String = "",
    val price: Int? = 0,
    val image: String = "",
    val inventory: Int? = 0,
    val placed: Int? = 0,
    val originalPrice: Int? = 0,
    val quantity: Int? = 0,
    val sold: Int? = 0,
    val productType: String ?= ""
)