package com.lhb.kiotviet_quanly.model

data class Bill(
    val id: String ?= "",
    val customerName: String ?= "",
    val date: String ?= "",
    val productId: String ?= "",
    val productName: String ?= "",
    val quantity: Int ?= 0,
    val totalAmount: Int ?= 0,
    val paymentMrthod: String ?= "",
    val profit: Int ?= 0
)
