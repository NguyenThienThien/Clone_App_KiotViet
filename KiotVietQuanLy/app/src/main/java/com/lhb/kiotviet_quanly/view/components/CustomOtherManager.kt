package com.lhb.kiotviet_quanly.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppSettingsAlt
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lhb.kiotviet_quanly.R

@Composable
fun Content1(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffeff1f3))
            .clip(RoundedCornerShape(15.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.White)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
                Column {
                    CustomTextFontSize(
                        title = "FastFood",
                        color = Color.Black,
                        fontSize = 20
                    )
                    CustomTextFontSizeMedium(
                        title = "Chi nhánh trung tâm",
                        color = Color(0xff5f6d74)
                    )
                }
            }
            Column(verticalArrangement = Arrangement.Center) {
                Icon(
                    Icons.Default.Create,
                    contentDescription = "null",
                    tint = Color(0xff748086)
                )
            }
        }
        Divider()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(Color(0xFFffffff))
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            CustomTextFontSizeMedium(
                title = "Thông tin cửa hàng",
                color = Color.Black,
                fontSize = 18
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .height(35.dp)
                        .width(90.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFeaeaea)),
                    contentAlignment = Alignment.Center
                ) {
                    CustomTextFontSizeMedium(title = "Còn 8 ngày", color = Color.Black)
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Icon(
                    Icons.Default.ArrowForwardIos,
                    contentDescription = null,
                    tint = Color(0xff748086),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun ContentTransaction(navController: NavController){
    Spacer(modifier = Modifier.padding(10.dp))
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        CustomTextFontSize(title = "Giao dịch", color = Color.Black, fontSize = 22)
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            CustomContentIcon(
                painter1 = painterResource(id = R.drawable.icon_1),
                title1 = "Hóa đơn",
                painter2 = painterResource(id = R.drawable.icon_2),
                title2 = "Đặt hàng",
                color = Color.Black,
                onClick1 = { /*TODO*/ },
                onClick2 = { navController.navigate("OrderScreen") }
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomContentIcon(
                painter1 = painterResource(id = R.drawable.icon_3),
                title1 = "Trả hàng",
                painter2 = painterResource(id = R.drawable.icon_4),
                title2 = "Sổ quỹ",
                color = Color.Black,
                onClick1 = { navController.navigate("ReturnGoodsScreen") },
                onClick2 = { /*TODO*/ }
            )
        }
    }
}

@Composable
fun ContentGoods(navController: NavController){
    Spacer(modifier = Modifier.padding(10.dp))
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        CustomTextFontSize(title = "Hàng hóa", color = Color.Black, fontSize = 22)
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            CustomContent3Icon(
                painter1 = painterResource(id = R.drawable.icon_5),
                title1 = "Hàng hóa",
                painter2 = painterResource(id = R.drawable.icon_6),
                title2 = "Nhập hàng",
                painter3 = painterResource(id = R.drawable.icon_7),
                title3 = "Chuyển hàng",
                color = Color.Black,
                onClick1 = { /*TODO*/ },
                onClick2 = { /*TODO*/ },
                onClick3 = { /*TODO*/ },
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomContent3Icon(
                painter1 = painterResource(id = R.drawable.icon_8),
                title1 = "Kiểm kho",
                painter2 = painterResource(id = R.drawable.icon_9),
                title2 = "Trả hàng nhập",
                painter3 = painterResource(id = R.drawable.icon_10),
                title3 = "Xuất hủy",
                color = Color.Black,
                onClick1 = { navController.navigate("CheckInventoryScreen") },
                onClick2 = { navController.navigate("ReturnImportedGoodsScreen") },
                onClick3 = { navController.navigate("CancelExportScreen") },
            )
        }
    }
}

@Composable
fun ContentSellOnline(){
    Spacer(modifier = Modifier.padding(10.dp))
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        CustomTextFontSize(title = "Bán online", color = Color.Black, fontSize = 22)
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            CustomContentIcon(
                painter1 = painterResource(id = R.drawable.icon_11),
                title1 = "Bán online",
                color = Color.Black,
                onClick1 = { /*TODO*/ },
                onClick2 = { /*TODO*/ }
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomContentIcon(
                painter1 = painterResource(id = R.drawable.icon_12),
                title1 = "Web bán hàng",
                color = Color.Black,
                onClick1 = { /*TODO*/ },
                onClick2 = { /*TODO*/ }
            )
        }
    }
}

@Composable
fun ContentPartner(){
    Spacer(modifier = Modifier.padding(10.dp))
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        CustomTextFontSize(title = "Đối tác", color = Color.Black, fontSize = 22)
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            CustomContentIcon(
                painter1 = painterResource(id = R.drawable.icon_13),
                title1 = "Khách hàng",
                color = Color.Black,
                onClick1 = { /*TODO*/ },
                onClick2 = { /*TODO*/ }
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomContentIcon(
                painter1 = painterResource(id = R.drawable.icon_14),
                title1 = "Nhà cung cấp",
                color = Color.Black,
                onClick1 = { /*TODO*/ },
                onClick2 = { /*TODO*/ }
            )
        }
    }
}

@Composable
fun ContentDelivery(){
    Spacer(modifier = Modifier.padding(10.dp))
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        CustomTextFontSize(title = "Giao hàng", color = Color.Black, fontSize = 22)
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            CustomContentIcon(
                painter1 = painterResource(id = R.drawable.icon_15),
                title1 = "Vận đơn",
                color = Color.Black,
                onClick1 = { /*TODO*/ },
                onClick2 = { /*TODO*/ }
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomContentIcon(
                painter1 = painterResource(id = R.drawable.icon_16),
                title1 = "Đối tác GH",
                color = Color.Black,
                onClick1 = { /*TODO*/ },
                onClick2 = { /*TODO*/ }
            )
        }
    }
}

@Composable
fun ContentStaff(){
    Spacer(modifier = Modifier.padding(10.dp))
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        CustomTextFontSize(title = "Nhân viên", color = Color.Black, fontSize = 22)
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            CustomContentIcon(
                painter1 = painterResource(id = R.drawable.icon_17),
                painter2 = painterResource(id = R.drawable.icon_19),
                title1 = "Nhân viên",
                title2 = "Chấm công",
                color = Color.Black,
                onClick1 = { /*TODO*/ },
                onClick2 = { /*TODO*/ }
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomContentIcon(
                painter1 = painterResource(id = R.drawable.icon_18),
                title1 = "Bảng lương",
                color = Color.Black,
                onClick1 = { /*TODO*/ },
                onClick2 = { /*TODO*/ }
            )
        }
    }
}

@Composable
fun ContentReport(){
    Spacer(modifier = Modifier.padding(10.dp))
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        CustomTextFontSize(title = "Báo cáo", color = Color.Black, fontSize = 22)
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            CustomContentIcon(
                painter1 = painterResource(id = R.drawable.icon_20),
                painter2 = painterResource(id = R.drawable.icon_22),
                title1 = "Cuối ngày",
                title2 = "Bán hàng",
                color = Color.Black,
                onClick1 = { /*TODO*/ },
                onClick2 = { /*TODO*/ }
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomContentIcon(
                painter1 = painterResource(id = R.drawable.icon_21),
                title1 = "Hàng hóa",
                color = Color.Black,
                onClick1 = { /*TODO*/ },
                onClick2 = { /*TODO*/ }
            )
        }
    }
}

@Composable
fun ContentFinance(){
    Spacer(modifier = Modifier.padding(10.dp))
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        CustomTextFontSize(title = "Tài chính", color = Color.Black, fontSize = 22)
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            CustomContentIcon(
                painter1 = painterResource(id = R.drawable.icon_23),
                title1 = "Thanh toán",
                color = Color.Black,
                onClick1 = { /*TODO*/ },
                onClick2 = { /*TODO*/ }
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomContentIcon(
                painter1 = painterResource(id = R.drawable.icon_24),
                title1 = "Vay vốn",
                color = Color.Black,
                onClick1 = { /*TODO*/ },
                onClick2 = { /*TODO*/ }
            )
        }
    }
}

@Composable
fun Content2(){
    Spacer(modifier = Modifier.padding(10.dp))
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.White)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Column {
                    CustomTextFontSize(
                        title = "KiotViet Quản lý",
                        color = Color.Black,
                        fontSize = 20
                    )
                    CustomTextFontSizeMedium(
                        title = "Sàn kết nối nguồn hàng giá tốt",
                        color = Color(0xff5f6d74)
                    )
                }
            }
            Column(verticalArrangement = Arrangement.Center) {
                Icon(
                    Icons.Default.ArrowForwardIos,
                    contentDescription = "null",
                    tint = Color(0xff748086)
                )
            }
        }
    }
}

@Composable
fun ContentGeneralSettings(){
    Spacer(modifier = Modifier.padding(10.dp))
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(15.dp),
        ) {
            CustomTextFontSize(title = "CÀI ĐẶT CHUNG", color = Color(0xff899298), fontSize = 20)
            Spacer(modifier = Modifier.padding(5.dp))
            CustomGeneralSettings(title = "Thiết lập cửa hàng", icon = Icons.Default.Settings)
            Spacer(modifier = Modifier.padding(5.dp))
            Column(modifier = Modifier.padding(10.dp)) {
                Divider()
            }
            CustomGeneralSettings(title = "Ứng dụng & thiết bị", icon = Icons.Default.AppSettingsAlt)
            Spacer(modifier = Modifier.padding(5.dp))
            Column(modifier = Modifier.padding(10.dp)) {
                Divider()
            }
            CustomGeneralSettings(title = "Quản lý người dùng", icon = Icons.Default.Person)
            Spacer(modifier = Modifier.padding(5.dp))
        }
    }
}

@Composable
fun ContentGeneralSettings2(){
    Spacer(modifier = Modifier.padding(5.dp))
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(15.dp),
        ) {
            CustomGeneralSettings(title = "Hướng dẫn sử dụng", icon = Icons.Default.QuestionMark)
            Spacer(modifier = Modifier.padding(5.dp))
            Column(modifier = Modifier.padding(10.dp)) {
                Divider()
            }
            CustomGeneralSettings(title = "Chat với chuyên viên hỗ trợ", icon = Icons.Default.ChatBubbleOutline, showTrailingIcon = false)
            Spacer(modifier = Modifier.padding(5.dp))
            Column(modifier = Modifier.padding(10.dp)) {
                Divider()
            }
            CustomGeneralSettings(title = "Gọi tổng đài 19006522", icon = Icons.Default.Phone, showTrailingIcon = false)
            Spacer(modifier = Modifier.padding(5.dp))
        }
    }
}

@Composable
fun ContentGeneralSettings3(){
    Spacer(modifier = Modifier.padding(10.dp))
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(15.dp),
        ) {
            CustomGeneralSettings(title = "Điều khoản sử dụng", icon = Icons.Default.Report)
            Spacer(modifier = Modifier.padding(5.dp))
            Column(modifier = Modifier.padding(10.dp)) {
                Divider()
            }
            CustomGeneralSettings(title = "Đăng xuất", icon = Icons.Default.Logout, showTrailingIcon = false, tint = Color.Red)
            Spacer(modifier = Modifier.padding(5.dp))
        }
    }
}

@Composable
fun CustomContentIcon(
    painter1: Painter?,
    title1: String,
    painter2: Painter? = null,
    title2: String = "",
    fontSize: Int = 20,
    color: Color,
    onClick1: () -> Unit,
    onClick2: () -> Unit,
){
    Column {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onClick1() }) {
            painter1?.let {
                Image(painter = it, contentDescription = "", modifier = Modifier.size(30.dp))
            }
            Spacer(modifier = Modifier.padding(10.dp))
            CustomTextFontSize(title = title1, color = color, fontSize = fontSize)
        }
        if (painter2 != null && title2.isNotEmpty()) {
            Spacer(modifier = Modifier.padding(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onClick2() }) {
                Image(painter = painter2, contentDescription = "", modifier = Modifier.size(30.dp))
                Spacer(modifier = Modifier.padding(10.dp))
                CustomTextFontSize(title = title2, color = color, fontSize = fontSize)
            }
        }
    }
}


@Composable
fun CustomContent3Icon(
    painter1: Painter,
    title1: String,
    painter2: Painter,
    title2: String,
    painter3: Painter,
    title3: String,
    fontSize: Int = 16,
    color: Color,
    onClick1: () -> Unit,
    onClick2: () -> Unit,
    onClick3: () -> Unit,
){
    Column{
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onClick1() }) {
            Image(painter = painter1, contentDescription = "", modifier = Modifier.size(30.dp))
            Spacer(modifier = Modifier.padding(10.dp))
            CustomTextFontSize(title = title1, color = color, fontSize = 20)
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onClick2() }) {
            Image(painter = painter2, contentDescription = "", modifier = Modifier.size(30.dp))
            Spacer(modifier = Modifier.padding(10.dp))
            CustomTextFontSize(title = title2, color = color, fontSize = 20)
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onClick3() }) {
            Image(painter = painter3, contentDescription = "", modifier = Modifier.size(30.dp))
            Spacer(modifier = Modifier.padding(10.dp))
            CustomTextFontSize(title = title3, color = color, fontSize = 20)
        }
    }
}

@Composable
fun CustomGeneralSettings(
    title: String,
    icon: ImageVector,
    showTrailingIcon: Boolean = true,
    tint: Color = Color.Black
){
    Spacer(modifier = Modifier.padding(4.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            modifier = Modifier.size(24.dp),
            tint = tint
        )
        Spacer(modifier = Modifier.width(5.dp))
        CustomTextFontSize(title = title, color = tint, fontSize = 18)
        Spacer(modifier = Modifier.weight(1f))
        if (showTrailingIcon) {
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}