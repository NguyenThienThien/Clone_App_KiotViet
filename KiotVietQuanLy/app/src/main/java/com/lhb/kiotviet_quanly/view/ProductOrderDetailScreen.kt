package com.lhb.kiotviet_quanly.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lhb.kiotviet_quanly.model.Product
import com.lhb.kiotviet_quanly.view.components.ContentFirst
import com.lhb.kiotviet_quanly.view.components.ContentSecond
import com.lhb.kiotviet_quanly.view.components.CustomButtonBlue
import com.lhb.kiotviet_quanly.view.components.CustomDivider
import com.lhb.kiotviet_quanly.view.components.InformationProduct

@Composable
fun ProductOrderDetailScreen(navController: NavController, product: Product, initialQuantity: Int) {
    var itemNumber by remember { mutableIntStateOf(initialQuantity) }
    var totalAmount by remember { mutableIntStateOf(product.price!! * initialQuantity) }
    var discountAmount by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = Color(0xfffefefe),
        modifier = Modifier
            .statusBarsPadding(),
        topBar = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Outlined.ArrowBackIosNew,
                    contentDescription = null,
                    modifier = Modifier.size(25.dp),
                    tint = Color(0xff4e5b67)
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .navigationBarsPadding()
                    .background(Color(0xffffffff))
                    .padding(10.dp)
            ) {
                CustomButtonBlue(
                    onClick = {
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("updatedPrice", totalAmount - discountAmount) // Cập nhật giá đã giảm
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("updatedProductId", product.id)
                        navController.popBackStack() // Quay lại màn hình trước
                    },
                    title = "Xong"
                )

                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(15.dp)) {
            InformationProduct(product = product)
            Divider()
            Column {
                ContentFirst(
                    initialQuantity = initialQuantity,
                    onItemSelectedNumber = { number ->
                        itemNumber = number
                        totalAmount = product.price!! * itemNumber
                    }
                )

                ContentSecond(title = "Đơn giá", price = product.price!!, onApplyDiscount = { discount ->
                    discountAmount = discount
                    totalAmount = (product.price!! * itemNumber) - discountAmount // Tính lại tổng tiền sau giảm giá
                })

                CustomDivider()
                ContentSecond(title = "Giảm giá", price = discountAmount, onApplyDiscount = { discount ->
                    discountAmount = discount
                })
                CustomDivider()
                ContentSecond(title = "Thành tiền", price = (totalAmount - discountAmount), onApplyDiscount = { discount ->
                    discountAmount = discount
                })
            }
        }
    }
}
