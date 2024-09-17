package com.lhb.kiotviett.View.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CardGiftcard
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lhb.kiotviett.View.navigator.ScreenNames

@Composable
fun BottomBarCart(
    navController: NavController,
    totalAmount: Int,
    totalProduct: Int
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color(0xffEFEFEF))
                        .padding(8.dp)
                ) {
                    Icon(Icons.Outlined.CardGiftcard, contentDescription = "", tint = Color(0xff303030))
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Khuyến mại",
                        color = Color(0xff303030),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Spacer(modifier = Modifier.width(15.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color(0xffEFEFEF))
                        .padding(8.dp)
                ) {
                    Icon(Icons.Outlined.Groups, contentDescription = "", tint = Color(0xff303030))
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "0",
                        color = Color(0xff303030),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            //
            Column(
                
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tổng tiền",
                        color = Color(0xff303030),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = totalProduct.toString(),
                        color = Color(0xff303030),
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xffEFEFEF))
                            .padding(5.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = totalAmount.toString(),
                    color = Color(0xff303030),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(7.dp))
                    .background(Color.Gray)
                    .padding(8.dp)
                    .clickable { navController.navigate(ScreenNames.OrderSummaryScreen.route) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Xem tạm tính",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 3.dp)
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(7.dp))
                    .background(Color(0xff0fa838))
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("${ScreenNames.PaymentScreen.route}/${totalAmount}/${totalProduct}")
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Thanh toán",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 3.dp)
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(7.dp))
                    .background(Color(0xff6b9fe3).copy(alpha = 0.8f))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Thông báo",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 3.dp)
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewBC(){
//    BottomBarCart()
//}
