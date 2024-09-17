package com.lhb.kiotviet_quanly.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.lhb.kiotviet_quanly.model.Product
import com.lhb.kiotviet_quanly.ui.theme.ColorEff1f3
import com.lhb.kiotviet_quanly.ui.theme.ColorTextBlue
import com.lhb.kiotviet_quanly.ui.theme.ColorTextGray
import com.lhb.kiotviet_quanly.ui.theme.ColorWhite
import com.lhb.kiotviet_quanly.utils.formatCurrency
import com.lhb.kiotviet_quanly.view.components.CustomTextFontSize
import com.lhb.kiotviet_quanly.view.components.InformationProduct
import com.lhb.kiotviet_quanly.view.components.ShowBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("InvalidColorHexValue")
@Composable
fun ProductDetailScreen(navController: NavController, product: Product){

    val checkedState = remember {
        mutableStateOf(false)
    }

    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    val sheetState = rememberModalBottomSheetState()

    Scaffold(
        backgroundColor = Color(0xffeff1f3),
        topBar = {
            Row(
                modifier = Modifier
                    .background(ColorEff1f3)
                    .fillMaxWidth()
                    .padding(5.dp)
                    .statusBarsPadding(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    androidx.compose.material3.Icon(Icons.Outlined.ArrowBackIosNew, contentDescription = "", tint = Color(0xff7d7f88), modifier = Modifier.size(24.dp))
                }
                IconButton(onClick = {
                    showBottomSheet = true
                }) {
                    androidx.compose.material3.Icon(Icons.Outlined.Menu, contentDescription = "", tint = Color(0xff7d7f88), modifier = Modifier.size(24.dp))
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)

        ) {
            item {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(ColorWhite)
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CustomTextFontSize(title = "THÔNG TIN CƠ BẢN", color = ColorTextGray, fontSize = 18)
                        CustomTextFontSize(title = "Sửa", color = ColorTextBlue, fontSize = 18, modifier = Modifier.clickable { navController.navigate("UpdateProduct/${product.id}") })
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    AsyncImage(
                        model = product.image,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    CustomTextFontSize(title = product.name, color = Color.Black, fontSize = 22)
                    InformationProduct(productCode = "Mã hàng", code = product.id, modifier = Modifier.fillMaxWidth())
                    Divider()
                    InformationProduct(productCode = "Mã vạch", code = "", modifier = Modifier.fillMaxWidth(), displayIcons = false)
                    Divider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(95.dp),
                    ) {
                        Column(modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(end = 25.dp)) {
                            InformationProduct(productCode = "Giá vốn", code = formatCurrency(product.originalPrice!!), displayIcons = false )
                            Divider()
                        }
                        Column(modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()) {
                            InformationProduct(productCode = "Giá bán", code = formatCurrency(product.price!!), displayIcons = false)
                            Divider()
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            InformationProduct(productCode = "Nhóm hàng", code = product.productType!!, displayIcons = false )
                            Divider()
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Column(modifier = Modifier
                            .weight(1f)
                            .padding(start = 14.dp)) {
                            InformationProduct(productCode = "Tồn kho", code = formatCurrency(product.inventory!!), displayIcons = false)
                            Divider()
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Default.ArrowForwardIos, contentDescription = null, modifier = Modifier.size(20.dp))
                        }

                    }
                }

                Spacer(modifier = Modifier.padding(8.dp))

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .clickable { navController.navigate("AddDescription") }
                        .background(ColorWhite)
                        .fillMaxWidth()
                        .padding(15.dp)

                ) {
                    CustomTextFontSize(title = "Thêm mô tả", color = ColorTextBlue, fontSize = 22)
                }

                Spacer(modifier = Modifier.padding(8.dp))

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(ColorWhite)
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        CustomTextFontSize(title = "Thêm định mức tồn kho", color = ColorTextGray, fontSize = 16)
                        CustomTextFontSize(title = "Sửa", color = ColorTextBlue, fontSize = 16, modifier = Modifier.clickable { navController.navigate("InventoryLevels") })
                    }

                    CustomTextFontSize(title = "0 - 10", color = Color.Black, fontSize = 20)
                }

                Spacer(modifier = Modifier.padding(8.dp))

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(ColorWhite)
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("WarehouseCardScreen") }, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        CustomTextFontSize(title = "Thẻ kho", color = Color.Black, fontSize = 18)
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Default.ArrowForwardIos, contentDescription = null, modifier = Modifier
                                .size(20.dp)
                                .clickable { navController.navigate("WarehouseCardScreen") })
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(8.dp))

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(ColorWhite)
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        CustomTextFontSize(title = "THÔNG TIN KHÁC", color = ColorTextGray, fontSize = 20)
                        CustomTextFontSize(title = "Sửa", color = ColorTextBlue, fontSize = 18, modifier = Modifier.clickable { navController.navigate("UpdateOtherInformationScreen") })
                    }
                    Spacer(modifier = Modifier.padding(3.dp))
                    CustomTextFontSize(title = "Trọng lượng (g)", color = ColorTextGray, fontSize = 16)
                    CustomTextFontSize(title = "0", color = Color.Black, fontSize = 16)
                }

                Spacer(modifier = Modifier.padding(8.dp))

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(ColorWhite)
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        CustomTextFontSize(title = "Bán trực tiếp", color = Color.Black, fontSize = 20)
                        Switch(
                            checked = checkedState.value,
                            onCheckedChange = { checkedState.value = it },
                            colors = androidx.compose.material3.SwitchDefaults.colors(
                                checkedThumbColor = Color.White, // Màu của nút tròn khi Switch được bật
                                uncheckedThumbColor = Color.White, // Màu của nút tròn khi Switch bị tắt
                                checkedTrackColor = Color(0xff006ff4), // Màu của thanh khi Switch được bật
                                uncheckedTrackColor = Color(0xffa9b1bc) // Màu của thanh khi Switch bị tắt
                            )
                        )


                    }
                }

                Spacer(modifier = Modifier.padding(20.dp))
                if(showBottomSheet){
                    ModalBottomSheet(
                        onDismissRequest = { showBottomSheet = false },
                        sheetState = sheetState,
                        dragHandle = {}
                    ){
                        Column(
                            modifier = Modifier
                                .navigationBarsPadding()
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            ShowBottomSheet()
                        }
                    }
                }
            }
        }
    }
}
