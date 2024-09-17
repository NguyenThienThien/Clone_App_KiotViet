package com.lhb.kiotviett.Model

import android.media.Image

data class Cart(
    val id: String,
    val name: String,
    val price: Int,
    val quantity: Int,
    val image: String
)
