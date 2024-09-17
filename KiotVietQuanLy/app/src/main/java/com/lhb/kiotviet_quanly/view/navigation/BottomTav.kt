package com.lhb.kiotviet_quanly.view.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AllInbox
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.InsertChartOutlined
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lhb.kiotviet_quanly.view.BillManagerScreen
import com.lhb.kiotviet_quanly.view.OtherManagerScreen
import com.lhb.kiotviet_quanly.view.OverViewManagerScreen
import com.lhb.kiotviet_quanly.view.ProductManagerScreen
import com.lhb.kiotviet_quanly.view.SellManagerScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomTav(navController: NavController, isBottomBarVisible: Boolean = true){
    val navigationController = rememberNavController()
    val selected = remember { mutableStateOf(Icons.Outlined.InsertChartOutlined) }
    var bottomBarVisible by remember { mutableStateOf(isBottomBarVisible) }

    fun navigateToScreen(icon: ImageVector, screen: String) {
        selected.value = icon
        navigationController.navigate(screen) {
            popUpTo(0)
        }
    }

    Scaffold(
        bottomBar = {
            if (bottomBarVisible) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .shadow(8.dp, RoundedCornerShape(0.dp))
                        .background(Color.Gray)
                ) {
                    BottomAppBar(
                        containerColor = Color(0xffffffff),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        IconButton(
                            onClick = {
                                selected.value = Icons.Outlined.InsertChartOutlined
                                navigationController.navigate(BottomBarScreens.OverViewManager.screen) {
                                    popUpTo(
                                        0
                                    )
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                Icons.Outlined.InsertChartOutlined,
                                contentDescription = "",
                                tint = if (selected.value == Icons.Outlined.InsertChartOutlined) Color(
                                    0xff005595
                                ) else Color(0xff7d7f88)
                            )
                        }
                        //
                        IconButton(
                            onClick = {
                                selected.value = Icons.Outlined.Description
                                navigationController.navigate(BottomBarScreens.BillManager.screen) {
                                    popUpTo(
                                        0
                                    )
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                Icons.Outlined.Description,
                                contentDescription = "",
                                tint = if (selected.value == Icons.Outlined.Description) Color(
                                    0xff005595
                                ) else Color(0xff7d7f88)
                            )
                        }
                        //
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            IconButton(
                                onClick = {
//                                selected.value = Icons.Outlined.ShoppingBag
//                                navigationController.navigate(BottomBarScreens.SellManager.screen) {
//                                    popUpTo(0)
//                                }
                                    navigateToScreen(
                                        Icons.Outlined.ShoppingBag,
                                        BottomBarScreens.SellManager.screen
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Icon(
                                    Icons.Outlined.ShoppingBag,
                                    contentDescription = "",
                                    tint = if (selected.value == Icons.Outlined.ShoppingBag) Color(
                                        0xff005595
                                    ) else Color(0xff7d7f88)
                                )
                            }
                            Text(
                                text = "Mới",
                                color = Color.White,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(top = 4.dp, end = 4.dp)
                                    .clip(RoundedCornerShape(15.dp))
                                    .background(Color.Red)
                                    .padding(horizontal = 5.dp)
                            )
                        }
                        //
                        IconButton(
                            onClick = {
                                selected.value = Icons.Outlined.AllInbox
                                navigationController.navigate(BottomBarScreens.ProductManager.screen) {
                                    popUpTo(
                                        0
                                    )
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                Icons.Outlined.AllInbox,
                                contentDescription = "",
                                tint = if (selected.value == Icons.Outlined.AllInbox) Color(
                                    0xff005595
                                ) else Color(0xff7d7f88)
                            )
                        }
                        //
                        IconButton(
                            onClick = {
                                selected.value = Icons.Outlined.Menu
                                navigationController.navigate(BottomBarScreens.OtherManager.screen) {
                                    popUpTo(
                                        0
                                    )
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                Icons.Outlined.Menu,
                                contentDescription = "",
                                tint = if (selected.value == Icons.Outlined.Menu) Color(0xff005595) else Color(
                                    0xff7d7f88
                                )
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = BottomBarScreens.OverViewManager.screen,
            modifier = Modifier.padding(
                start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                top = paddingValues.calculateTopPadding(),
                end = paddingValues.calculateEndPadding(LayoutDirection.Ltr))
        ){
            composable(BottomBarScreens.OverViewManager.screen){ OverViewManagerScreen(navController)}
            composable(BottomBarScreens.BillManager.screen){ BillManagerScreen(navController, ::navigateToScreen)}
            composable(BottomBarScreens.SellManager.screen) {
                SellManagerScreen(navController, onCartChange = { bottomBarVisible = it == 0 })
            }
            composable(BottomBarScreens.ProductManager.screen){ ProductManagerScreen(navController)}
            composable(BottomBarScreens.OtherManager.screen){ OtherManagerScreen(navController)}
        }
    }
}