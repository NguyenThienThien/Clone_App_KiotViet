package com.lhb.kiotviet_quanly.view

import DialogAddRetailCustomer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lhb.kiotviet_quanly.view.components.CustomTextFontSize
import com.lhb.kiotviet_quanly.view.components.ItemRetailCustomer
import com.lhb.kiotviet_quanly.viewmodel.RetailCustomerViewModel

@Composable
fun RetailCustomers(navController: NavController, retailCustomerViewModel: RetailCustomerViewModel = viewModel()) {
    val fakeCustomers by retailCustomerViewModel.retailCustomers.observeAsState(emptyList())
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            DialogAddRetailCustomer(onDismiss = {showDialog = false})
        }
    }

    Scaffold(
        containerColor = Color(0xffeff1f3),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .statusBarsPadding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Outlined.ArrowBackIosNew,
                        contentDescription = "",
                        modifier = Modifier.size(25.dp)
                    )
                }
                CustomTextFontSize(
                    title = "Khách hàng",
                    color = Color.Black,
                    fontSize = 24,
                    modifier = Modifier
                )
                IconButton(onClick = {
                    showDialog = true
                }) {
                    Icon(
                        Icons.Outlined.Add,
                        contentDescription = "",
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .clickable { }
        ) {
            LazyColumn {
                items(fakeCustomers.size) { index ->
                    ItemRetailCustomer(
                        retailCustomer = fakeCustomers[index],
                        clickToSelectRetailCustomer = {
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                "selectedCustomerName",
                                fakeCustomers[index].name
                            )
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun RetailCustomersPreview() {
    RetailCustomers(navController = rememberNavController())
}