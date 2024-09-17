package com.lhb.kiotviet_quanly.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lhb.kiotviet_quanly.ui.theme.OpenSans
import com.lhb.kiotviet_quanly.utils.formatCurrency

@Composable
fun BottomBarOrderDetail(
    productNumber: Int,
    totalAmount: Int,
    onClickPay: () -> Unit,
    onClickAddToCard: () -> Unit,
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .fillMaxWidth()
            .padding(10.dp)
            .navigationBarsPadding()
    ) {
        Spacer(modifier = Modifier.padding(5.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Tổng tiền hàng", fontSize = 20.sp, fontWeight = FontWeight(500))
                Spacer(modifier = Modifier.padding(4.dp))
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xff4081e4),
                            shape = RoundedCornerShape(60.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = productNumber.toString(),
                        color = Color(0xff4081e4),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            Text(text = formatCurrency(totalAmount), fontSize = 20.sp, fontWeight = FontWeight(500))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            Button(
                onClick = { onClickAddToCard() },
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .border(
                        width = 2.dp,
                        color = Color(0xff3f86f7),
                        shape = RoundedCornerShape(10.dp)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                shape = RectangleShape
            ) {
                Text(
                    text = "Lưu tạm",
                    fontSize = 22.sp,
                    color = Color(0xff3f86f7),
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(
                onClick = { onClickPay() },
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .clip(RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff3f86f7)
                ),
                shape = RectangleShape
            ) {
                Text(
                    text = "Thanh toán",
                    fontSize = 22.sp,
                    color = Color.White,
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.padding(5.dp))
    }
}