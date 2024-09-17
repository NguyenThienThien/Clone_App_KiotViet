package com.lhb.kiotviet_quanly.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lhb.kiotviet_quanly.R
import com.lhb.kiotviet_quanly.view.components.CustomTextFontSize
import com.lhb.kiotviet_quanly.view.components.TopBarAll

@Composable
fun ReturnImportedGoodsScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopBarAll(
                onClickToSearch = { /*TODO*/ },
                onClickToSort = { /*TODO*/ },
                onClickToFilter = { /*TODO*/ },
                onClickTopShowDateBottomSheet = { /*TODO*/ },
                onClickToBack = { navController.popBackStack() },
                title = "Trả hàng nhập"
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xffeff1f3))
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_bill),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            CustomTextFontSize(title = "Chưa có giao dịch", color = Color(0xff525d69), fontSize = 18)
        }
    }
}