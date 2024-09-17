package com.lhb.kiotviet_quanly.view.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lhb.kiotviet_quanly.ui.theme.OpenSans

@Composable
fun CustomTextFontSize(
    title: String,
    color: Color,
    fontSize: Int = 16,
    modifier: Modifier = Modifier
){
    Text(
        text = title,
        fontSize = fontSize.sp,
        color = color,
        fontFamily = OpenSans,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun CustomTextFontSizeMedium(
    title: String,
    color: Color,
    fontSize: Int = 16
){
    Text(
        text = title,
        fontSize = fontSize.sp,
        color = color,
        fontFamily = OpenSans,
        fontWeight = FontWeight(600),
    )
}

@Composable
fun CustomTextOnClick(
    title: String,
    color: Color,
    fontSize: Int = 16,
    onclick: () -> Unit
){
    Text(
        text = title,
        fontSize = fontSize.sp,
        color = color,
        fontFamily = OpenSans,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .clickable { onclick() }
    )
}