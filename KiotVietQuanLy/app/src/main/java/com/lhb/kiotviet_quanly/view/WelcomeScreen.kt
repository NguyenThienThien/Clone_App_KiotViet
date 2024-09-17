package com.lhb.kiotviet_quanly.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lhb.kiotviet_quanly.R
import com.lhb.kiotviet_quanly.utils.isLoggedIn
import com.lhb.kiotviet_quanly.utils.isOnBoardingCompleted
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(navController: NavController) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val nextScreen = if (isLoggedIn(context)) {
            "bottomTav"
        } else if (isOnBoardingCompleted(context)) {
            "SignInScreen"
        } else {
            "OnBoardingScreen"
        }
        delay(3000) // Thời gian hiển thị WelcomeScreen
        navController.navigate(nextScreen) {
            popUpTo("WelcomeScreen") { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_welcome),
            contentDescription = "",
            modifier = Modifier.size(90.dp)
        )
    }
}

@Composable
@Preview
fun WelcomeScreenPreview() {
    WelcomeScreen(navController = rememberNavController())
}
