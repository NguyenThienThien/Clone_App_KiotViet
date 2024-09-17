package com.lhb.kiotviett.View

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lhb.kiotviett.Model.Cart
import com.lhb.kiotviett.View.component.ItemOrderSummary
import com.lhb.kiotviett.View.component.TopBarOrderSummary

@Composable
fun OrderSummaryScreen(
    navController: NavController
){
    val fakeCart = listOf(
        Cart("c1", "San Pham 1", 50000, 2 , ""),
        Cart("c1", "San Pham 2", 50000, 1 , ""),
        Cart("c1", "San Pham 3", 50000, 3 , ""),
    )

    val totalItem = fakeCart.sumOf { it.quantity }
    val totalAmount = fakeCart.sumOf { it.quantity * it.price}

    Scaffold(
        containerColor = Color(0xffF0F0F0),
        modifier = Modifier
            .statusBarsPadding(),
        topBar = {
            TopBarOrderSummary(
                title = "Xem tạm tính 1-5",
                onBackClick = {navController.popBackStack()}
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn {
                item {
                    Text(text = "Khác", fontSize = 16.sp, fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    ){
                        Text(
                            text = "Khác", fontSize = 16.sp, fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(10.dp),
                            color = Color(0xff005595)
                        )
                    }
                }
                items(fakeCart.size){index ->
                    ItemOrderSummary(item = fakeCart[index])
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 35.dp)
                    ) {
                        Divider(
                            color = Color.LightGray,
                            thickness = 1.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(horizontal = 10.dp, vertical = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ){
                            Text(
                                text = "Tổng tiền hàng",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xff303030),
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = totalItem.toString(),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xff303030),
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xff303030).copy(alpha = 0.4f),
                                        shape = RoundedCornerShape(5.dp)
                                    )
                                    .padding(horizontal = 6.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = totalAmount.toString(),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xff303030),
                            )
                        }
                        Divider(
                            color = Color.LightGray,
                            thickness = 1.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp)
                        )
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(horizontal = 10.dp, vertical = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(
                                text = "Giảm giá (0%)",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xff303030),
                            )
                            Text(
                                text = "0",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xff303030),
                            )
                        }
                        Divider(
                            color = Color.LightGray,
                            thickness = 1.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp)
                        )
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(horizontal = 10.dp, vertical = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(
                                text = "Thu khác",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xff303030),
                            )
                            Text(
                                text = "0",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xff303030),
                            )
                        }
                        Divider(
                            color = Color.LightGray,
                            thickness = 1.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp)
                        )
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(horizontal = 10.dp, vertical = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(
                                text = "Khách cần trả",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xff303030),
                            )
                            Text(
                                text = totalAmount.toString(),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xff303030),
                            )
                        }
                        Divider(
                            color = Color.LightGray,
                            thickness = 1.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                item { 
                    Box(modifier = Modifier.height(150.dp))
                }
            }
        }
    }
}