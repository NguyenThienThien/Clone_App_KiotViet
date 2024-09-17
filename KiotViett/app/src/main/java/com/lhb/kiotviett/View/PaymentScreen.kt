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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Close
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lhb.kiotviett.View.component.ButtonChoosePaymentMethod
import com.lhb.kiotviett.View.component.CustomBigButton
import com.lhb.kiotviett.View.component.CustomOutlinedTextField1
import com.lhb.kiotviett.View.component.DialogNotification
import com.lhb.kiotviett.View.component.ToggleBottom
import com.lhb.kiotviett.View.component.TopBarOrderSummary
import com.lhb.kiotviett.View.component.TopBarPayment
import com.lhb.kiotviett.View.navigator.ScreenNames
import com.lhb.kiotviett.View.navigator.ScreenNavigation

@Composable
fun PaymentScreen(
    totalAmount: Int,
    totalProduct: Int,
    navController: NavController
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
                                    text = totalProduct.toString(),
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
                                text = totalAmount.toString(),
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
                            Row {
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
                                ToggleBottom()
                                Spacer(modifier = Modifier.width(5.dp))
                                CustomOutlinedTextField1(
                                    value = discount,
                                    onValueChange = { discount = it },
                                    modifier = Modifier
                                )
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
                                text = "Khác cần trả",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xff303030),
                            )
                            Text(
                                text = totalAmount.toString(),
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
                                navController.navigate("${ScreenNames.PaymentMethodScreen.route}/${totalAmount}")
                            })
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(text = "Tiền khách trả",
                                color = Color(0xff303030),
                                fontWeight = FontWeight.Normal,
                                fontSize = 17.sp
                            )
                            Text(text = totalAmount.toString(),
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

@Composable
@Preview
fun PreviewProductOrderDetailScreen(){
    PaymentScreen(totalAmount = 2, totalProduct = 2, navController = rememberNavController())
}