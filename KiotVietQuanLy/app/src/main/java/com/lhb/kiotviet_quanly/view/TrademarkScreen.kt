package com.lhb.kiotviet_quanly.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lhb.kiotviet_quanly.R
import com.lhb.kiotviet_quanly.ui.theme.ColorEff1f3
import com.lhb.kiotviet_quanly.ui.theme.ColorTextBlue
import com.lhb.kiotviet_quanly.view.components.CustomTextFontSize

@Composable
fun TradeMarkScreen(navController: NavController){
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .background(ColorEff1f3)
                    .fillMaxWidth()
                    .padding(0.dp)
                    .statusBarsPadding(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment =  Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        androidx.compose.material3.Icon(Icons.Outlined.ArrowBackIosNew, contentDescription = "", tint = Color(0xff454a4f), modifier = Modifier.size(24.dp))
                    }
                    CustomTextFontSize(title = "Thương hiệu", color = Color.Black, fontSize = 18)
                }
                IconButton(onClick = { navController.navigate("NewTrademarkScreen") }) {
                    androidx.compose.material3.Icon(Icons.Outlined.Add, contentDescription = "", tint = Color(0xff454a4f), modifier = Modifier.size(24.dp))
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.icon_product), contentDescription = null, modifier = Modifier.size(100.dp))
            Spacer(modifier = Modifier.padding(10.dp))
            CustomTextFontSize(title = "Chưa có thương hiệu", color = Color.Black, fontSize = 18)
            Spacer(modifier = Modifier.padding(5.dp))
            Button(
                onClick = { navController.navigate("NewTrademarkScreen") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff0070f3)
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp)),
                shape = RectangleShape,
            ) {
                CustomTextFontSize(title = "Thêm mới", color = Color.White, fontSize = 16)
            }
        }
    }
}