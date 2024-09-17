package com.lhb.kiotviet_quanly.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

@SuppressLint("CommitPrefEdits")
fun setOnBoardingCompleted(context: Context){
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("onBoardingCompleted", true)
    editor.apply()
}

fun isOnBoardingCompleted(context: Context): Boolean {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("onBoardingCompleted", false)
}