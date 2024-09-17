package com.lhb.kiotviet_quanly.view.components

import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.CloseFullscreen
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lhb.kiotviet_quanly.viewmodel.ProductTypeViewModel

@Composable
fun TopBarProduct(
    sizeProduct: Int ?= 0,
    totalInventory: Int= 0,
    selectedProductType: String?,
    onProductTypeSelected: (String) -> Unit,
    onClickSortByPrice: () -> Unit,
    onClickToSearch: () -> Unit,
    onClickToSortByName: () -> Unit,
    productTypeViewModel: ProductTypeViewModel = viewModel()
){

    var expanded by remember { mutableStateOf(false) }
    val listProductType by productTypeViewModel.productType.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xfff0f0f0))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 15.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Hàng hóa", fontSize = 28.sp, color = Color(0xff303030), fontWeight = FontWeight.Bold)
                Row {
                    IconButton(onClick = { onClickToSearch() }) {
                        Icon(Icons.Outlined.Search, contentDescription = "", tint = Color(0xff7d7f88), modifier = Modifier.size(24.dp))
                    }
                    IconButton(onClick = {  }) {
                        Icon(Icons.Outlined.CloseFullscreen, contentDescription = "", tint = Color(0xff7d7f88), modifier = Modifier.size(23.dp))
                    }
                    IconButton(onClick = {  }) {
                        Icon(Icons.Outlined.MoreHoriz, contentDescription = "", tint = Color(0xff7d7f88), modifier = Modifier.size(23.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { onClickToSortByName() }) {
                    Icon(Icons.Outlined.FilterList, contentDescription = "", tint = Color(0xff303030),
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xffb6b6b6).copy(alpha = 0.3f))
                            .padding(5.dp)
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Box {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .clickable { expanded = true }
                            .background(Color(0xffEFEFEF))
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = selectedProductType ?: "Tất cả loại hàng",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xff303030)
                        )
                        Icon(Icons.Outlined.ArrowDropDown, contentDescription = "", tint = Color.Gray)
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                onProductTypeSelected("Tất cả loại hàng")
                            }
                        ) {
                            Text(text = "Tất cả loại hàng")
                        }

                        listProductType.forEach { productType ->
                            DropdownMenuItem(
                                onClick = {
                                    expanded = false
                                    onProductTypeSelected(productType.name!!)
                                }
                            ) {
                                Text(text = productType.name!!)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .clickable { onClickSortByPrice() }
                        .background(Color(0xffEFEFEF))
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Giá bán", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xff303030))
                    Icon(Icons.Outlined.ArrowDropDown, contentDescription = "", tint = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Tổng tồn", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color(0xff303030))
                        Icon(Icons.Outlined.ArrowDropDown, contentDescription = "", tint = Color.Gray)
                    }
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(text = sizeProduct.toString(), fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xff404040))
                }
                Text(text = totalInventory.toString(), fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color(0xff303030))
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffF0F0F0))
            .height(15.dp))
    }
}
