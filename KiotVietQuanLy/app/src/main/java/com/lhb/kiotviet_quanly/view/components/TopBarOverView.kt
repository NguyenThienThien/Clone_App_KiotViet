package com.lhb.kiotviet_quanly.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lhb.kiotviet_quanly.R

@Composable
fun TopBarOverView(
    onClickToNotification: () -> Unit,
    onClickToEmail: () -> Unit,
    onClickTopShowBottomSheetDate: () -> Unit,
    formattedDate: String? = null
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_kiot), contentDescription = "",
                modifier = Modifier.height(50.dp)
            )
            Row {
                IconButton(onClick = { onClickToNotification() }) {
                    Icon(Icons.Outlined.Notifications, contentDescription = "", tint = Color(0xff7d7f88), modifier = Modifier.size(24.dp))
                }
                IconButton(onClick = { onClickToEmail() }) {
                    Icon(Icons.Outlined.Email, contentDescription = "", tint = Color(0xff7d7f88), modifier = Modifier.size(23.dp))
                }
            }
        }
        //
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .clip(RoundedCornerShape(15.dp))
                .clickable {
                    onClickTopShowBottomSheetDate()
                }
                .background(Color(0xffEFEFEF))
                .padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = formattedDate!!, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color(0xff303030))
            Icon(Icons.Outlined.ArrowDropDown, contentDescription = "", tint = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBarOverView(){
    TopBarOverView(
        onClickToNotification = { /*TODO*/ },
        onClickToEmail = {},
        onClickTopShowBottomSheetDate = {}
    )
}