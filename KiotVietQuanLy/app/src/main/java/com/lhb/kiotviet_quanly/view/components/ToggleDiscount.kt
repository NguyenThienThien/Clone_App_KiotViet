package com.lhb.kiotviet_quanly.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToggleDiscount(
    isSelected: (Boolean) -> Unit,
){
    var selected by remember { mutableStateOf("VND") }
    Row(
        modifier = Modifier
            .width(110.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xffE8E8E8))
            .padding(2.dp)
            .height(41.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ToggleOptions(
            text = "VND",
            isSelected = selected == "VND",
            onClick = { selected = "VND" }
        )
        ToggleOptions(
            text = "%",
            isSelected = selected == "%",
            onClick = { selected = "%" }
        )
    }
}
@Composable
fun ToggleOptions(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(55.dp)
            .height(41.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) Color(0xffffffff) else Color.Transparent)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) Color(0xff005595) else Color.Gray,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}