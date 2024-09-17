package com.lhb.kiotviett.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.More
import androidx.compose.material.icons.outlined.Sort
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lhb.kiotviett.Model.Cart
import com.lhb.kiotviett.View.component.BottomBarCart
import com.lhb.kiotviett.View.component.ItemCart
import com.lhb.kiotviett.View.component.TopBarCart

@Composable
fun CartScreen(
    navController: NavController
) {
    val fakeCart = listOf(
        Cart("c1", "San Pham 1", 50000, 2 , ""),
        Cart("c1", "San Pham 2", 50000, 1 , ""),
        Cart("c1", "San Pham 3", 50000, 3 , ""),
    )
    val totalAmount = fakeCart.sumOf { it.quantity * it.price }
    val totalProduct = fakeCart.sumOf { it.quantity }
    Scaffold(
        containerColor = Color(0xffF0F0F0),
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            Column {
                TopBarCart(title = "1-5", onBackClick = {navController.popBackStack()})
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xffF0F0F0))
                        .padding(start = 15.dp, end = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {

                        },
                    ) {
                        Icon(
                            Icons.Outlined.Sort,
                            contentDescription = null,
                            tint = Color(0xff303030),
                            modifier = Modifier
                                .clip(RoundedCornerShape(30.dp))
                                .background(Color(0xffffffff))
                                .padding(5.dp)
                                .size(26.dp),
                        )
                    }
                    TextButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Text(
                            text = "Phòng VIP 1 / Phòng VIP",
                            color = Color(0xff303030),
                            fontSize = 16.sp,
                            modifier = Modifier
                                .clip(RoundedCornerShape(15.dp))
                                .background(Color.White)
                                .padding(10.dp)

                        )
                    }
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color.White)
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Outlined.More,
                            contentDescription = "",
                            tint = Color(0xff303030),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Bảng giá chung",
                            color = Color(0xff303030),
                            fontSize = 16.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color(0xff014e8a))
                    .padding(5.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "",
                    modifier = Modifier.size(28.dp),
                    tint = Color.White
                )
            }
        },
        bottomBar = {
            BottomBarCart(navController, totalAmount = totalAmount, totalProduct = totalProduct)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn {
                items(fakeCart.size){ index ->
                    ItemCart(itemCart = fakeCart[index])
                }
                item {
                    Box(modifier = Modifier.height(200.dp))
                }
            }
        }
    }
}