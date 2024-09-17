package com.lhb.kiotviet_quanly.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lhb.kiotviet_quanly.view.components.BottomBarSellScreen
import com.lhb.kiotviet_quanly.view.components.ItemProduct
import com.lhb.kiotviet_quanly.view.components.ToggleBottom
import com.lhb.kiotviet_quanly.view.components.TopBarOverView
import com.lhb.kiotviet_quanly.view.components.TopBarSell
import com.lhb.kiotviet_quanly.viewmodel.ProductViewModel

@Composable
fun SellManagerScreen(
    navController: NavController,
    onCartChange: (Int) -> Unit,
    productViewModel: ProductViewModel = viewModel()
) {
    val products by productViewModel.products.observeAsState(emptyList())
    var isSell by remember { mutableStateOf(false) }
    var selectedItems by remember { mutableStateOf(emptyList<Boolean>()) }
    var itemCartNumber by remember { mutableIntStateOf(0) }

    LaunchedEffect(products) {
        selectedItems = List(products.size) { false }
    }

    onCartChange(itemCartNumber)

    Scaffold(
        containerColor = Color(0xffF0F0F0),
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            TopBarSell(
                title = if (isSell) "Bán hàng" else "Đặt hàng",
                onClickToQR = { /*TODO*/ },
                onClickToAdd = {}
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(80.dp)
                    .navigationBarsPadding()
            )
            if (itemCartNumber != 0) {
                BottomBarSellScreen(
                    onClickToCancel = {
                        itemCartNumber = 0
                        selectedItems = List(products.size) { false }
                    },
                    onClickToSaveCart = {
                        val selectedProductIds = products.filterIndexed { index, _ -> selectedItems.getOrElse(index) { false } }
                            .joinToString(separator = "|") { it.id }
                        navController.navigate("OrderDetailScreen/$selectedProductIds")
                    },
                    productNumber = itemCartNumber
                )
            }
        },
        floatingActionButton = {
            if (itemCartNumber == 0) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.LightGray)
                            .padding(top = 1.dp, start = 2.dp, end = 2.dp, bottom = 3.dp)
                    ) {
                        ToggleBottom(
                            isSelected = { isSell = it }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn {
                items(products.size) { index ->
                    val isSelected = selectedItems.getOrElse(index) { false }
                    products.getOrNull(index)?.let {
                        ItemProduct(
                            product = it,
                            isSelected = isSelected,
                            onCLick = {
                                if (isSelected) {
                                    itemCartNumber -= 1
                                } else {
                                    itemCartNumber += 1
                                }
                                selectedItems = selectedItems.toMutableList().apply {
                                    if (index < size) {
                                        this[index] = !isSelected
                                    }
                                }
                                onCartChange(itemCartNumber)
                            },
                            onItemSelectedNumber = { number ->

                            }
                        )
                    }
                }
            }
        }
    }
}
