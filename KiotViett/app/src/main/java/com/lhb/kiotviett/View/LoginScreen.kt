package com.lhb.kiotviett.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lhb.kiotviett.R
import com.lhb.kiotviett.View.component.CustomBigButton
import com.lhb.kiotviett.View.component.CustomOutlinedTextField
import com.lhb.kiotviett.View.component.CustomOutlinedTextField1
import com.lhb.kiotviett.View.component.TopBarSingle
import com.lhb.kiotviett.View.navigator.ScreenNames

@Composable
fun LoginScreen(navController: NavController){
    var tenGianHangState by remember { mutableStateOf("") }
    var tenDangNhapState by remember { mutableStateOf("") }
    var matKhauState by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color(0xffffffff),
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Image(
                        painter = painterResource(id = R.drawable.logo1), contentDescription = "",
                        modifier = Modifier.padding(top = 15.dp).height(60.dp)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Image(
                        painter = painterResource(id = R.drawable.logo2), contentDescription = "",
                        modifier = Modifier
                            .height(60.dp)
                            .clip(RoundedCornerShape(15.dp))
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Bar - Cafe, Nhà hàng, Karaoke & Billiards",
                    color = Color(0xff005595),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(45.dp))
                        .background(Color(0xffF5F5F5))
                        .padding(vertical = 3.dp, horizontal = 15.dp)
                )
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Divider(color = Color.LightGray, thickness = 0.5.dp, modifier = Modifier.weight(1f))
                Row(modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xfff5f5f5))
                    .padding(vertical = 8.dp, horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.Call, contentDescription = "", tint = Color(0xff303030))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Hỗ trợ", color = Color(0xff303030))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "1900 6522", color = Color(0xff005595))
                }
                Divider(color = Color.LightGray, thickness = 0.5.dp, modifier = Modifier.weight(1f))
                Row(modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xfff5f5f5))
                    .padding(vertical = 8.dp, horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_viet), contentDescription = "",
                        modifier = Modifier.height(25.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "VI", color = Color(0xff303030))
                }
                Divider(color = Color.LightGray, thickness = 0.5.dp, modifier = Modifier.weight(1f))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bán hàng", modifier = Modifier.padding(top = 40.dp, bottom = 20.dp),
                fontWeight = FontWeight.SemiBold, fontSize = 20.sp
            )
            CustomOutlinedTextField(value = tenGianHangState, onValueChange = {tenGianHangState = it}, label = "Tên gian hàng", modifier = Modifier)
            Spacer(modifier = Modifier.height(20.dp))
            CustomOutlinedTextField(value = tenDangNhapState, onValueChange = {tenDangNhapState = it}, label = "Tên đăng nhập", modifier = Modifier)
            Spacer(modifier = Modifier.height(20.dp))
            CustomOutlinedTextField(value = matKhauState, onValueChange = {matKhauState = it}, label = "Mật khẩu", modifier = Modifier)

            Spacer(modifier = Modifier.height(40.dp))
            CustomBigButton(title = "Đăng nhập", onClick = {navController.navigate(ScreenNames.DrawerNavigationScreen.route)})

            Spacer(modifier = Modifier.height(60.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(text = "Bạn chưa có tài khoản?", color = Color(0xff303030), fontSize = 18.sp)
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(
                    onClick = { navController.navigate(ScreenNames.SignUpScreen.route)}
                ) {
                    Text(text = "Đăng ký ngay", color = Color(0xff005595), fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}