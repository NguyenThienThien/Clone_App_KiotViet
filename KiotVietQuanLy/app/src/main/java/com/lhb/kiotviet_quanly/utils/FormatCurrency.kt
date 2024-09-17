package com.lhb.kiotviet_quanly.utils

import java.text.NumberFormat
import java.util.Locale

fun formatCurrency(amount: Int): String {
    return NumberFormat.getNumberInstance(Locale.US).format(amount)
}