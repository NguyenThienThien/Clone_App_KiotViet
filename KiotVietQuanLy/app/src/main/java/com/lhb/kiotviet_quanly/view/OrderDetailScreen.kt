package com.lhb.kiotviet_quanly.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lhb.kiotviet_quanly.model.Product
import com.lhb.kiotviet_quanly.view.components.BottomBarOrderDetail
import com.lhb.kiotviet_quanly.view.components.ItemOrderDetail
import com.lhb.kiotviet_quanly.view.components.TopBarOrderDetail
import com.lhb.kiotviet_quanly.viewmodel.CartViewModel

@Composable
fun OrderDetailScreen(
    navController: NavController,
    selectedProducts: MutableList<Product>,  // Chuyển sang MutableList
    cartViewModel: CartViewModel = viewModel()
) {
    val selectedCustomerName = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.get<String>("selectedCustomerName")

    var quantities = remember {
        mutableStateListOf<Int>().apply {
            addAll(selectedProducts.map { 1 })
        }
    }

    var totalAmount by remember { mutableIntStateOf(0) }
    var totalQuantity by remember { mutableIntStateOf(0) }

    fun calculateTotalAmount() {
        totalAmount = 0
        totalQuantity = 0
        selectedProducts.zip(quantities).forEach { (product, quantity) ->
            totalAmount += product.price!! * quantity
            totalQuantity += quantity
        }
    }

    val context = LocalContext.current

    LaunchedEffect(cartViewModel.isCartAdded) {
        if (cartViewModel.isCartAdded) {
            Toast.makeText(context, "Lưu trữ đơn hàng thành công", Toast.LENGTH_SHORT).show()
        }
    }

    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val updatedPriceFlow = savedStateHandle?.getStateFlow<Int>("updatedPrice", 0)
    val updatedPrice by updatedPriceFlow!!.collectAsState()

    val updatedProductIdFlow = savedStateHandle?.getStateFlow<String>("updatedProductId", "")
    val updatedProductId by updatedProductIdFlow!!.collectAsState()

    LaunchedEffect(updatedPrice, updatedProductId) {
        val productIndex = selectedProducts.indexOfFirst { it.id == updatedProductId }
        if (productIndex != -1) {
            selectedProducts[productIndex] = selectedProducts[productIndex].copy(price = updatedPrice) // Cập nhật giá đã giảm
            calculateTotalAmount() // Tính lại tổng tiền
        }
    }

    calculateTotalAmount()

    Scaffold(
        containerColor = Color(0xffeff1f3),
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            TopBarOrderDetail(
                onClickToQR = { /*TODO*/ },
                onClickToAdd = {},
                clickRetailCustomer = { navController.navigate("RetailCustomersScreen") },
                navController,
                nameCustomer = selectedCustomerName
            )
        },
        bottomBar = {
            BottomBarOrderDetail(
                productNumber = totalQuantity,
                totalAmount = totalAmount,
                onClickAddToCard = {
                    if (selectedCustomerName.isNullOrBlank()) {
                        Toast.makeText(context, "Vui lòng chọn khách hàng trước lưu trữ đơn hàng", Toast.LENGTH_SHORT).show()
                    } else {
                        navController.popBackStack() // Giữ dòng này để quay lại trang trước
                        cartViewModel.addCart(
                            customerName = selectedCustomerName,
                            totalAmount = totalAmount,
                            selectedProducts = selectedProducts,
                            quantities = quantities
                        )
                    }
                },
                onClickPay = {
                    if (selectedCustomerName.isNullOrBlank()) {
                        Toast.makeText(context, "Vui lòng chọn khách hàng trước khi thanh toán", Toast.LENGTH_SHORT).show()
                    } else {
                        navController.navigate("Payment/${totalAmount}/${totalQuantity}/${selectedCustomerName}")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            Spacer(modifier = Modifier.padding(10.dp))
            LazyColumn(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xffffffff))
            ) {
                items(selectedProducts.size) { index ->
                    ItemOrderDetail(
                        product = selectedProducts[index],
                        onCLick = {
                            navController.navigate("ProductOrderDetailScreen/${selectedProducts[index].id}/${quantities[index]}")
                        },
                        onItemSelectedNumber = { number ->
                            quantities[index] = number
                            calculateTotalAmount()
                        }
                    )
                }
            }
        }
    }
}
