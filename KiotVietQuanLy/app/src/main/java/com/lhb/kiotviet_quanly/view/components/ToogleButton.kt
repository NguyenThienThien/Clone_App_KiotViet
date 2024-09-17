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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToggleBottom(
    isSelected: (Boolean) -> Unit,
){
    var selected by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xffffffff))
            .padding(2.dp)
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ToggleOption(
            text = "Đặt hàng",
            isSelected = !selected,
            onClick = { selected = false
            isSelected(false)
            }
        )
        ToggleOption(
            text = "Bán hàng",
            isSelected = selected,
            onClick = { selected = true
                isSelected(true)
            }
        )
    }
}
@Composable
fun ToggleOption(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(37.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) Color(0xff005595) else Color.Transparent)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) Color(0xffffffff) else Color(0xff303030),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}