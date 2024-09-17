package com.lhb.kiotviet_quanly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.lhb.kiotviet_quanly.ui.theme.KiotViet_QuanLyTheme
import com.lhb.kiotviet_quanly.view.navigation.ScreenNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KiotViet_QuanLyTheme {
                ScreenNavigation()
            }
        }
    }
}
