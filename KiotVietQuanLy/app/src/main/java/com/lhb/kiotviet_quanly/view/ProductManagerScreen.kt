package com.lhb.kiotviet_quanly.view

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lhb.kiotviet_quanly.view.components.ItemProduct2
import com.lhb.kiotviet_quanly.view.components.SearchBox
import com.lhb.kiotviet_quanly.view.components.TopBarProduct
import com.lhb.kiotviet_quanly.viewmodel.ProductViewModel

@Composable
fun ProductManagerScreen(
    navController: NavController,
    productViewModel: ProductViewModel = viewModel()
) {
    val products by productViewModel.products.observeAsState(emptyList())
    val totalInventory = products.sumOf { it.inventory!! }
    val isLoading by productViewModel.isLoading.observeAsState(false)
    val selectedProductType by productViewModel.selectedProductType.observeAsState("Tất cả loại hàng")
    var isSearchActive by remember { mutableStateOf(false) }
    var searchByName by remember { mutableStateOf("") }
    val context = LocalContext.current

    val filteredProducts = if (selectedProductType == "Tất cả loại hàng") {
        products
    } else {
        products.filter { it.productType == selectedProductType }
    }

    LaunchedEffect(searchByName) {
        productViewModel.searchProductsByName(searchByName)
    }

    Scaffold(
        containerColor = Color(0xffF0F0F0),
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            AnimatedVisibility(
                visible = isSearchActive,
                enter = fadeIn(animationSpec = tween(durationMillis = 300)) + scaleIn(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                ),
                exit = fadeOut(animationSpec = tween(durationMillis = 300)) + scaleOut(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                )
            ) {
                SearchBox(
                    text = searchByName,
                    onTextChanged = { searchByName = it },
                    onClearClick = {
                        searchByName = ""
                        isSearchActive = false

                    },
                    onSearchClick = {

                    }
                )
            }

            AnimatedVisibility(
                visible = !isSearchActive,
                enter = fadeIn(animationSpec = tween(durationMillis = 300)) + scaleIn(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                ),
                exit = fadeOut(animationSpec = tween(durationMillis = 300)) + scaleOut(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                )
            ) {
                TopBarProduct(
                    sizeProduct = filteredProducts.size,
                    totalInventory = totalInventory,
                    onClickSortByPrice = {
                        productViewModel.sortProductsByPrice()
                        val sortOrder = if (productViewModel.isAscending) "Tăng dần" else "Giảm dần"
                        Toast.makeText(
                            context,
                            "Đang sắp xếp theo giá: $sortOrder",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    selectedProductType = selectedProductType,
                    onProductTypeSelected = { type ->
                        productViewModel.setSelectedProductType(type)
                        Toast.makeText(
                            context,
                            "Đang lọc sản phẩm theo loại: $type",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    onClickToSearch = {
                        isSearchActive = true
                    },
                    onClickToSortByName = {
                        productViewModel.sortProductsByName()
                    }
                )
            }
        },
        floatingActionButton = {
            IconButton(
                onClick = { navController.navigate("AddProductScreen") },
                modifier = Modifier
                    .padding(bottom = 70.dp)
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(bottom = 70.dp)
                .pointerInput(Unit) {
                    detectTapGestures {
                        if (isSearchActive) {
                            isSearchActive = false
                        }
                    }
                }
        ) {
            LazyColumn {
                items(filteredProducts) { product ->
                    AnimatedVisibility(
                        visible = true,
                        enter = slideInHorizontally(
                            animationSpec = tween(durationMillis = 300),
                            initialOffsetX = { it }
                        ) + fadeIn(animationSpec = tween(durationMillis = 300)),
                        exit = fadeOut(animationSpec = tween(durationMillis = 300))
                    ) {
                        ItemProduct2(
                            product = product,
                            isLoading = isLoading,
                            onClickItem = {
                                if (!isSearchActive) {
                                    navController.navigate("ProductDetailScreen/${product.id}")
                                } else {
                                    isSearchActive = false
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
