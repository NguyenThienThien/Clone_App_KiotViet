package com.lhb.kiotviet_quanly.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lhb.kiotviet_quanly.ui.theme.ColorEff1f3
import com.lhb.kiotviet_quanly.ui.theme.ColorTextBlue
import com.lhb.kiotviet_quanly.ui.theme.ColorTextGray
import com.lhb.kiotviet_quanly.ui.theme.ColorWhite
import com.lhb.kiotviet_quanly.view.components.CustomTextEnter
import com.lhb.kiotviet_quanly.view.components.CustomTextFontSize

@Composable
fun AddDescriptionScreen(navController: NavController){
    var newDescription by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color(0xffeff1f3),
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
                        androidx.compose.material3.Icon(Icons.Outlined.ArrowBackIosNew, contentDescription = "", tint = Color(0xff7d7f88), modifier = Modifier.size(24.dp))
                    }
                    CustomTextFontSize(title = "Thêm mô tả", color = Color.Black, fontSize = 18)
                }
                CustomTextFontSize(title = "Lưu", color = ColorTextBlue, fontSize = 18, modifier = Modifier
                    .padding(end = 15.dp)
                    .clickable { })
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).clip(RoundedCornerShape(10.dp)).background(
                ColorWhite
            ).fillMaxWidth()
        ) {
            CustomTextEnter(value = newDescription, onValueChange = {newDescription = it}, label = "Thêm mô tả")
        }
    }

}

@Composable
@Preview
fun AddDescriptionScreenPreview(){
    AddDescriptionScreen(rememberNavController())
}