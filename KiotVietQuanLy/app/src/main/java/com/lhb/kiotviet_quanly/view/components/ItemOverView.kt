package com.lhb.kiotviet_quanly.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lhb.kiotviet_quanly.R

@Composable
fun ItemOverView(
    iconName: Painter,
    title: String,
    size: Int,
    onCLick: () -> Unit
){
    Column(
        modifier = Modifier.background(Color.Transparent).clickable { onCLick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painter = iconName, contentDescription = "", tint = Color(0xff0066CC), modifier = Modifier.size(size.dp))
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = title, fontSize = 14.sp, color = Color(0xff303030))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemOverView(){
    ItemOverView(iconName = painterResource(id = R.drawable.icon_money), "Hoang Long",20, onCLick = {})
}

