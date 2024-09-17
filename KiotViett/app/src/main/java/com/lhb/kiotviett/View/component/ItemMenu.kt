package com.lhb.kiotviett.View.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt

@Composable
fun ItemMenu(
    menu: String,
    isSelected: Boolean,
    onClick: () -> Unit
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor =  if (isSelected) Color("#065695".toColorInt()) else Color.White,
            contentColor = Color("#ffffff".toColorInt())
        ),
        modifier = Modifier
            .padding(end = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .height(45.dp)
            .clickable { onClick() },
    ){
        Box(
            modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = menu,
                color = if(isSelected) Color(0xffffffff) else Color(0xff303030),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}