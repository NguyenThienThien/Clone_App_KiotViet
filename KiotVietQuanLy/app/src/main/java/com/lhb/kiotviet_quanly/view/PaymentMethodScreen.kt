package com.lhb.kiotviet_quanly.view

import com.lhb.kiotviet_quanly.view.components.CustomBigButton
import com.lhb.kiotviet_quanly.view.components.TopBarSingle
import com.lhb.kiotviett.View.component.ItemCash
import com.lhb.kiotviett.View.component.ItemPayOnline
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lhb.kiotviet_quanly.utils.formatCurrency

@Composable
fun PaymentMethodScreen(
    totalAmount: Int,
    navController: NavController
){
    var selectedMethod by remember { mutableStateOf("Cash") }
    Scaffold(
        containerColor = Color(0xffF0F0F0),
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            TopBarSingle(
                title = "Phương thức thanh toán",
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
                    title = "Lưu lại",
                    onClick = {navController.popBackStack()}
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
                    Column(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Divider(color = Color.LightGray, thickness = 0.5.dp, modifier = Modifier.fillMaxWidth())
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Khách cần trả", fontSize = 18.sp, color = Color(0xff303030))
                            Text(text = formatCurrency(totalAmount), fontSize = 18.sp, color = Color(0xff005595), fontWeight = FontWeight.Bold)
                        }
                        Divider(color = Color.LightGray, thickness = 0.5.dp, modifier = Modifier.fillMaxWidth())
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Khách thanh toán", fontSize = 18.sp, color = Color(0xff303030))
                            Text(text = formatCurrency(totalAmount), fontSize = 18.sp, color = Color(0xff303030), fontWeight = FontWeight.Bold)
                        }
                    }
                }
                item{
                    Text(
                        text = "PHƯƠNG THỨC THANH TOÁN", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xff505050),
                        modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 20.dp)
                    )
                }
                item {
                    ItemCash(totalAmount, selectedMethod == "Cash") { selectedMethod = "Cash" }
                    ItemPayOnline(totalAmount, "Thẻ", selectedMethod == "Card") { selectedMethod = "Card" }
                    ItemPayOnline(totalAmount, "Chuyển khoản", selectedMethod == "BankTransfer") { selectedMethod = "BankTransfer" }
                }
                item {
                    Box(modifier = Modifier.height(200.dp))
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewPaymentMethodScreen(){
    PaymentMethodScreen(totalAmount = 100000, navController = rememberNavController())
}