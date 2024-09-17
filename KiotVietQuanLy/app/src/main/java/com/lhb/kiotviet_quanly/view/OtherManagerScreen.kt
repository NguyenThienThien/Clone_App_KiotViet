package com.lhb.kiotviet_quanly.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lhb.kiotviet_quanly.view.components.Content1
import com.lhb.kiotviet_quanly.view.components.Content2
import com.lhb.kiotviet_quanly.view.components.ContentDelivery
import com.lhb.kiotviet_quanly.view.components.ContentFinance
import com.lhb.kiotviet_quanly.view.components.ContentGeneralSettings
import com.lhb.kiotviet_quanly.view.components.ContentGeneralSettings2
import com.lhb.kiotviet_quanly.view.components.ContentGeneralSettings3
import com.lhb.kiotviet_quanly.view.components.ContentGoods
import com.lhb.kiotviet_quanly.view.components.ContentPartner
import com.lhb.kiotviet_quanly.view.components.ContentReport
import com.lhb.kiotviet_quanly.view.components.ContentSellOnline
import com.lhb.kiotviet_quanly.view.components.ContentStaff
import com.lhb.kiotviet_quanly.view.components.ContentTransaction

@Composable
fun OtherManagerScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .background(Color(0xffeff1f3))
            .fillMaxSize()

    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            item {
                Content1()

                ContentTransaction(navController)

                ContentGoods(navController)

                ContentSellOnline()

                ContentPartner()

                ContentDelivery()

                ContentStaff()

                ContentReport()

                ContentFinance()

                Content2()

                ContentGeneralSettings()

                ContentGeneralSettings2()

                ContentGeneralSettings3()
            }
            item {
                Spacer(modifier = Modifier.padding(60.dp))
            }

        }
    }
}

@Composable
@Preview
fun OtherManagerScreenPreview() {
    OtherManagerScreen(navController = rememberNavController())
}