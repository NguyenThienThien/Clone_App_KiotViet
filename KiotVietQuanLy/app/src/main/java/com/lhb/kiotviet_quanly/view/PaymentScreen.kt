package com.lhb.kiotviet_quanly.view

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lhb.kiotviet_quanly.utils.formatCurrency
import com.lhb.kiotviet_quanly.view.components.ButtonChoosePaymentMethod
import com.lhb.kiotviet_quanly.view.components.CustomBigButton
import com.lhb.kiotviet_quanly.view.components.CustomOutlinedTextField1
import com.lhb.kiotviet_quanly.view.components.DialogNotification
import com.lhb.kiotviet_quanly.view.components.ToggleBottom
import com.lhb.kiotviet_quanly.view.components.TopBarPayment

@Composable
fun PaymentScreen(
    navController: NavController,
    totalAmount: Int,
    totalQuantity: Int,
    nameCustomer: String
) {
    var discount by remember { mutableStateOf("") }
    var showDialogConfirm by remember { mutableStateOf(false) }
    Scaffold(
        containerColor = Color(0xffF0F0F0),
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            TopBarPayment(
                title = "Thanh toán",
                onBackClick = {navController.popBackStack()}
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(10.dp)
            ) {
                CustomBigButton(
                    title = "Hoàn thành",
                    onClick = { showDialogConfirm = true }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn {
                item {
                    Text(
                        text = "TỔNG ĐƠN", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Gray,
                        modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                    )
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Divider(
                            color = Color.LightGray,
                            thickness = 0.5.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(horizontal = 10.dp, vertical = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                Text(
                                    text = "Tổng tiền hàng",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xff303030),
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = totalQuantity.toString(),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xff303030),
                                    modifier = Modifier
                                        .border(
                                            width = 1.dp,
                                            color = Color(0xff303030).copy(alpha = 0.4f),
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .clip(RoundedCornerShape(15.dp))
                                        .background(Color(0xffF0F0F0))
                                        .padding(horizontal = 6.dp)
                                )
                            }
                            Text(
                                text = formatCurrency(totalAmount),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        Divider(
                            color = Color.LightGray,
                            thickness = 0.5.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(horizontal = 10.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(modifier = Modifier.height(35.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Giảm giá",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xff303030),
                                )
                                Spacer(modifier = Modifier.padding(end = 5.dp))
                                Icon(
                                    Icons.Outlined.Edit,
                                    contentDescription = ""
                                )
                            }

                            Row {
                                Spacer(modifier = Modifier.width(5.dp))
                            }
                        }
                        Divider(
                            color = Color.LightGray,
                            thickness = 0.5.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(horizontal = 10.dp, vertical = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Khách cần trả",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xff303030),
                            )
                            Text(
                                text = formatCurrency(totalAmount),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xff005595)
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = "KHÁCH HÀNG THANH TOÁN", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Gray,
                        modifier = Modifier.padding(top = 40.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                    )
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Divider(
                            color = Color.LightGray,
                            thickness = 0.5.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(horizontal = 10.dp, vertical = 20.dp),
                        ) {
                            ButtonChoosePaymentMethod(onClick = {
                                navController.navigate("PaymentMethodScreen/$totalAmount")
                            })
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(text = "Tiền khách trả",
                                color = Color(0xff303030),
                                fontWeight = FontWeight.Normal,
                                fontSize = 17.sp
                            )
                            Text(text = formatCurrency(totalAmount),
                                color = Color(0xff303030),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Divider(
                            color = Color.LightGray,
                            thickness = 0.5.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                item {
                    Divider(
                        color = Color.LightGray,
                        thickness = 0.5.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(horizontal = 10.dp, vertical = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Tiền thừa trả khách",
                            color = Color(0xff303030),
                            fontWeight = FontWeight.Normal,
                            fontSize = 17.sp
                        )
                        Text(text = "0",
                            color = Color(0xff303030),
                            fontWeight = FontWeight.Normal,
                            fontSize = 17.sp
                        )
                    }
                }
                item {
                    Box(modifier = Modifier.height(150.dp))
                }
            }
        }
    }
    if(showDialogConfirm){
        DialogNotification(
            onAgreeClick = {
                // dialog thong bao tao hoa don thanh cong
            },
            onDismissClick = {
                showDialogConfirm = false
            }
        )
    }
}