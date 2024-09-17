package com.lhb.kiotviett.View.navigator

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.BorderBottom
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Difference
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kiotviet.avtivities.HomeScreen
import com.example.kiotviet.avtivities.component.ToolBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DrawerNavigation(navController: NavController){
    val navigationController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val isExpanded = remember { mutableStateOf(true) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column {
                            Text(
                                text = "Nguyễn Thiên Thiên",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF002a54)
                            )

                            Spacer(modifier = Modifier.padding(2.dp))

                            Text(
                                text = "Chi nhánh trung tâm",
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF002a54)
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(
                            onClick = {
                                isExpanded.value = !isExpanded.value
                            }
                        ) {
                            if(isExpanded.value) {
                                Icon(
                                    Icons.Default.ArrowForwardIos,
                                    contentDescription = null,
                                    modifier = Modifier.size(25.dp)
                                )
                            }else{
                                Icon(
                                    Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    modifier = Modifier.size(35.dp)
                                )
                            }
                        }
                    }
                }
                if(isExpanded.value){
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        NavigationItem(
                            text = "Phòng ban",
                            icon = Icons.Default.BorderBottom,
                            onClick = { /*TODO*/ },
                            navigationController = navigationController,
                            coroutineScope = coroutineScope,
                            drawerState = drawerState,
                        )
                        NavigationItem(
                            text = "Đồng bộ dữ liệu",
                            icon = Icons.Default.Cached,
                            onClick = { /*TODO*/ },
                            navigationController = navigationController,
                            coroutineScope = coroutineScope,
                            drawerState = drawerState
                        )
                        NavigationItem(
                            text = "Thông báo dữ liệu",
                            icon = Icons.Default.Notifications,
                            onClick = { /*TODO*/ },
                            navigationController = navigationController,
                            coroutineScope = coroutineScope,
                            drawerState = drawerState
                        )
                        NavigationItem(
                            text = "Yêu cầu thanh Toán",
                            icon = Icons.Default.AttachMoney,
                            onClick = { /*TODO*/ },
                            navigationController = navigationController,
                            coroutineScope = coroutineScope,
                            drawerState = drawerState
                        )
                        NavigationItem(
                            text = "Lịch sử đơn hàng",
                            icon = Icons.Default.History,
                            onClick = { /*TODO*/ },
                            navigationController = navigationController,
                            coroutineScope = coroutineScope,
                            drawerState = drawerState
                        )
                        NavigationItem(
                            text = "Báo cáo mỗi ngày",
                            icon = Icons.Default.Difference,
                            onClick = { /*TODO*/ },
                            navigationController = navigationController,
                            coroutineScope = coroutineScope,
                            drawerState = drawerState
                        )
                        Divider()
                        Spacer(modifier = Modifier.padding(3.dp))
                        NavigationItem(
                            text = "Thiết lập",
                            icon = Icons.Default.Settings,
                            onClick = { /*TODO*/ },
                            navigationController = navigationController,
                            coroutineScope = coroutineScope,
                            drawerState = drawerState
                        )
                        NavigationItem(
                            text = "Hỗ trợ",
                            icon = Icons.Default.Phone,
                            onClick = { /*TODO*/ },
                            navigationController = navigationController,
                            coroutineScope = coroutineScope,
                            drawerState = drawerState,
                            additionalText = "1900 6522"
                        )
                        Divider()
                        Spacer(modifier = Modifier.padding(3.dp))
                        NavigationItem(
                            text = "Xóa tài khoản",
                            icon = Icons.Default.Person,
                            onClick = { /*TODO*/ },
                            navigationController = navigationController,
                            coroutineScope = coroutineScope,
                            drawerState = drawerState
                        )
                        NavigationItem(
                            text = "Ngôn ngữ",
                            icon = Icons.Default.Language,
                            onClick = { /*TODO*/ },
                            navigationController = navigationController,
                            coroutineScope = coroutineScope,
                            drawerState = drawerState
                        )
                        NavigationDrawerItem(
                            modifier = Modifier.background(Color.White),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = Color.White,
                                unselectedContainerColor = Color.White
                            ),
                            label = { Text(text = "Đăng xuất", fontSize = 18.sp, fontWeight = FontWeight(500), color = Color.Red) },
                            icon = { Icon(Icons.Default.Logout, contentDescription = null, tint = Color.Red) },
                            selected = false,
                            onClick = {
                                coroutineScope.launch { drawerState.close() }
                                navController.navigate(ScreenNames.LoginScreen.route) { popUpTo(0) }
                            }
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                    }
                }else{
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(5.dp)
                    ) {
                        NavigationDrawerItem(
                            modifier = Modifier.background(Color.White),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = Color.White,
                                unselectedContainerColor = Color.White
                            ),
                            label = {
                                Row(
                                    modifier = Modifier.fillMaxWidth(), // Ensures that the Row takes up the full width
                                    horizontalArrangement = Arrangement.SpaceBetween, // Spaces the text and icon apart
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Chi nhánh trung tâm",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight(500),
                                        color = Color.Black
                                    )
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = Color.Blue,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            },
                            selected = false,
                            onClick = {
                                coroutineScope.launch { drawerState.close() }
//                                { popUpTo(0) }
                                navController.navigate(ScreenNames.HomeScreen.route)
                            }
                        )
                        Divider()
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                ToolBar {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }
            }
        ) {
            NavHost(
                navController = navigationController,
                startDestination = ScreenNames.HomeScreen.route
            ) {
                composable(ScreenNames.HomeScreen.route){ HomeScreen(navController = navController) }
            }
        }
    }
}

@Composable
fun NavigationItem(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    navigationController: NavController,
    coroutineScope: CoroutineScope,
    drawerState: androidx.compose.material3.DrawerState,
    additionalText: String? = null // Optional phone number
) {
    NavigationDrawerItem(
        modifier = Modifier.background(Color.White),
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = Color.White,
            unselectedContainerColor = Color.White,
        ),
        label = {
            Row {
                Text(
                    text = text,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color.Black
                )
                if (additionalText != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = additionalText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Color.Blue
                    )
                }
            }
        },
        icon = { Icon(icon, contentDescription = null) },
        selected = false,
        onClick = {
            coroutineScope.launch { drawerState.close() }
            navigationController.navigate("Home") { popUpTo(0) }
        }
    )
    Spacer(modifier = Modifier.padding(5.dp))
}
