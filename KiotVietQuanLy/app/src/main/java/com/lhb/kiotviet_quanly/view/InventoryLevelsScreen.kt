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
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.IconButton
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
import com.lhb.kiotviet_quanly.view.components.CustomTextEnter
import com.lhb.kiotviet_quanly.view.components.CustomTextFontSize
import com.lhb.kiotviet_quanly.view.components.CustomTextSelected

@Composable
fun InventoryLevels(navController: NavController){
    var note by remember { mutableStateOf("10") }
    var weight by remember { mutableStateOf("0") }

    Scaffold(
        backgroundColor = Color(0xffeff1f3),
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
                    CustomTextFontSize(title = "Định mức tồn kho", color = Color.Black, fontSize = 18)
                }
                CustomTextFontSize(title = "Lưu", color = ColorTextBlue, fontSize = 18, modifier = Modifier.padding(end = 15.dp).clickable {  })
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).clip(RoundedCornerShape(10.dp))) {
            CustomTextEnter(
                value = weight,
                onValueChange = {weight = it},
                label = "Tốn ít nhất",
            )

            CustomTextEnter(
                value = note,
                onValueChange = {note = it},
                label = "Tốn nhiều nhất",
            )
        }
    }
}