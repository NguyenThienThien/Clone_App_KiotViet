package com.lhb.kiotviet_quanly.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomBarSellScreen(
    onClickToCancel: () -> Unit,
    onClickToSaveCart: () -> Unit,
    productNumber: Int
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White)
            .clip(RoundedCornerShape(10.dp))
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        TextButton(
            modifier = Modifier
                .weight(1f)
                .border(
                    width = 1.dp,
                    color = Color(0xff0070f3),
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Transparent),
            onClick = { onClickToCancel() }
        ) {
            Text(
                text = "Bỏ chọn",
                color = Color(0xff303030),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
        TextButton(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xff0070f3)),
            onClick = { onClickToSaveCart() }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Xong",
                    color = Color(0xffffffff),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = productNumber.toString(),
                    color = Color(0xff0070f3),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .width(25.dp)
                        .height(25.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color(0xfffffeff))
                        .padding(3.dp),
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomBarSellScreenPreview() {
    BottomBarSellScreen(
        onClickToCancel = {},
        onClickToSaveCart = {},
        productNumber = 3
    )
}