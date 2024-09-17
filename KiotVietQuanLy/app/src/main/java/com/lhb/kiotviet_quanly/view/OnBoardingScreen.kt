package com.lhb.kiotviet_quanly.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lhb.kiotviet_quanly.R
import com.lhb.kiotviet_quanly.utils.setOnBoardingCompleted
import com.lhb.kiotviet_quanly.view.components.BottomSheetPhoneNumber
import com.lhb.kiotviet_quanly.view.components.CustomButtonBlue
import com.lhb.kiotviet_quanly.view.components.CustomButtonTransparent
import com.lhb.kiotviet_quanly.view.components.CustomTextFontSize
import com.lhb.kiotviet_quanly.view.components.CustomTextOnClick
import com.lhb.kiotviet_quanly.view.components.PagerScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
    val pagerState = rememberPagerState(0, 0F) { 5 }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    setOnBoardingCompleted(context)

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        // TopBar
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .statusBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_haha),
                    contentDescription = "logo",
                    modifier = Modifier
                        .width(150.dp)
                        .height(100.dp)
                )
            }
        },
        // TopBar
        // BottomBar
        bottomBar = {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .navigationBarsPadding()
            ) {
                if (pagerState.currentPage == 0) {
                    Column(modifier = Modifier.padding(15.dp)) {
                        CustomButtonTransparent(
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(1)
                                }
                            },
                            title = "Bắt đầu"
                        )
                        Spacer(modifier = Modifier.padding(70.dp))
                    }
                } else {
                    Column(modifier = Modifier.padding(15.dp)) {
                        CustomButtonBlue(
                            onClick = {
                                navController.navigate("SignUpScreen") {
                                    popUpTo("OnBoardingScreen") { inclusive = true }
                                }
                            },
                            title = "Tạo tài khoản miễn phí"
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        CustomButtonTransparent(
                            onClick = {
                                navController.navigate("SignInScreen"){
                                    popUpTo("OnBoardingScreen") { inclusive = true }
                                }
                            },
                            title = "Đã có tài khoản? Đăng nhập"
                        )
                        Spacer(modifier = Modifier.padding(35.dp))
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CustomTextFontSize(
                        title = "Tổng đài hỗ trợ",
                        color = Color.Black,)
                    Spacer(modifier = Modifier.padding(5.dp))
                    CustomTextOnClick(
                        title = "1900 6522",
                        color = Color(0xff3f86f7),
                        onclick = { showBottomSheet = true })
                }

                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = { showBottomSheet = false },
                        sheetState = sheetState,
                        dragHandle = {},
                        containerColor = Color.Transparent
                    ) {
                        BottomSheetPhoneNumber {
                            showBottomSheet = false
                        }
                    }
                }
            }
        }
        // BottomBar
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            PagerScreen(pagerState)
        }
    }

}

@Composable
@Preview
fun OnBoardingScreenPreview() {
    OnBoardingScreen(navController = rememberNavController())
}