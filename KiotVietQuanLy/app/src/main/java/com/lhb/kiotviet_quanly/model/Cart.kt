package com.lhb.kiotviet_quanly.model

data class Cart(
    val id: String? = "",
    val customerName: String? = "",
    val totalAmount: Int? = 0,
    val cartStatus: String? = "",
    val items: List<Item> = listOf()
)

data class Item(
    val id: String? = "",
    val productId: String? = "",
    val productName: String? = "",
    val quantity: Int? = 0,
    val price: Int? = 0,
    val image: String? = ""
)
