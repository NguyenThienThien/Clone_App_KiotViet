package com.lhb.kiotviett.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lhb.kiotviett.Model.FoodDrink
import com.lhb.kiotviett.View.component.BottomBarProductScreen
import com.lhb.kiotviett.View.component.ItemFoodDrink
import com.lhb.kiotviett.View.component.ItemMenu
import com.lhb.kiotviett.View.component.TopBar
import com.lhb.kiotviett.View.navigator.ScreenNames

@Composable
fun FoodAndDrinkScreen(
    navController: NavController
) {
    val fakeProduct = listOf(
        FoodDrink("p1", "Name 1", 50000, "","Bia & Thuốc Lá"),
        FoodDrink("p2", "Name 2", 50000, "","Classic Cocktails"),
        FoodDrink("p3", "Name 3", 50000, "","Classic Cocktails"),
        FoodDrink("p4", "Name 4", 50000, "","Bia & Thuốc Lá"),
        FoodDrink("p5", "Name 5", 50000, "","Món Khai Vị"),
        FoodDrink("p6", "Name 6", 50000, "","Món Khai Vị"),
        FoodDrink("p7", "Name 7", 50000, "","Món Chính"),
        FoodDrink("p8", "Name 8", 50000, "","Món Chính"),
        FoodDrink("p9", "Name 9", 50000, "","Súp"),
        FoodDrink("p10", "Name 10", 50000, "","Súp"),
        FoodDrink("p11", "Name 11", 50000, "","Tea"),
        FoodDrink("p12", "Name 12", 50000, "","Tea"),
    )

    val fakeMenu = listOf(
        "Tất cả", "Bia & Thuốc Lá", "Classic Cocktails", "Món Khai Vị", "Món Chính", "Súp", "Tea"
    )
    var selectedIndex by remember { mutableIntStateOf(0) }

    var itemCartNumber by remember { mutableIntStateOf(0) }

    val filterProduct = if(selectedIndex == 0){
        fakeProduct
    }else{
        fakeProduct.filter { it.type == fakeMenu[selectedIndex] }
    }

    Scaffold(
        containerColor = Color(0xffffffff),
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TopBar(title = "Phòng VIP 1", onBackClick = {navController.popBackStack()},
                    onClickAdd = {navController.navigate(ScreenNames.CreateProductScreen.route)}
                )
                Spacer(modifier = Modifier.height(5.dp))
                LazyRow(
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                ) {
                    items(fakeMenu.size) { index ->
                        ItemMenu(
                            menu = fakeMenu[index],
                            isSelected = index == selectedIndex,
                            onClick = {
                                selectedIndex = index
                            }
                        )
                    }
                }
            }
        },
        bottomBar = {
            if(itemCartNumber != 0){
                BottomBarProductScreen(
                    onClickToCancel = {
                        itemCartNumber = 0
                    },
                    onClickToSaveCart = { navController.navigate(ScreenNames.CartScreen.route) },
                    productNumber = 1
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 15.dp),
        ) {
            LazyColumn {
                items(filterProduct.size) { index ->
                    ItemFoodDrink(
                        foodDrink = filterProduct[index],
                        onClick = {
                            itemCartNumber += 1
                        },
                        onItemSelectedNumber = { number ->

                        }
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewScreen() {
//    FoodAndDrinkScreen()
//}