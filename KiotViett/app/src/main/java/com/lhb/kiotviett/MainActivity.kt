package com.lhb.kiotviett

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.lhb.kiotviett.View.navigator.ScreenNavigation
import com.lhb.kiotviett.ui.theme.KiotViettTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KiotViettTheme {
                ScreenNavigation()
            }
        }
    }
}
