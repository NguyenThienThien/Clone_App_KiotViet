package com.lhb.kiotviett.View.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommodityGroup(onClick: () -> Unit){

    val listCommodityGroup = listOf(
        "Đồ ăn","Đồ uống","Khác"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = { onClick() },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 5.dp)
            ) {
                Icon(
                    Icons.Outlined.Close, contentDescription = "",
                    modifier = Modifier.size(26.dp),
                    tint = Color(0xff303030)
                )
            }
            Text(
                text = "Thêm hàng hóa",
                color = Color(0xff303030),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
            )
        }
        Divider()

        LazyColumn {
            items(listCommodityGroup.size) { index ->
                Column(
                    modifier = Modifier
                        .clickable {  }
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(10.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = listCommodityGroup[index],
                            fontSize = 20.sp,
                            fontWeight = FontWeight(500)
                        )
                    }

                    Divider()
                }
            }
        }

    }
}