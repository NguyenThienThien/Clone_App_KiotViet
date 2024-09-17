package com.lhb.kiotviet_quanly.view.components

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AspectRatio
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lhb.kiotviet_quanly.model.ItemMenu
import com.lhb.kiotviet_quanly.ui.theme.ColorTextBlue
import com.lhb.kiotviet_quanly.ui.theme.ColorTextGray

@Composable
fun InformationProduct(
    productCode: String,
    code: String,
    displayIcons: Boolean = true,
    modifier: Modifier = Modifier
){
    Spacer(modifier = Modifier.padding(6.dp))
    Column(
        modifier = modifier.height(80.dp),
    ) {
        CustomTextFontSize(title = productCode, color = ColorTextGray, fontSize = 18)
        Row(

            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomTextFontSize(title = code, color = Color.Black, fontSize = 20)
            Spacer(modifier = Modifier.padding(3.dp))
            if(displayIcons){
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Code, contentDescription = null, modifier = Modifier.size(24.dp), tint = ColorTextGray)
                }
            }
        }
    }
}

@Composable
fun ShowBottomSheet() {

    val listMenuProductDetails = listOf(
        ItemMenu(Icons.Default.AspectRatio, "In tem mã"),
        ItemMenu(Icons.Default.Lock, "Ngừng kinh doanh"),
        ItemMenu(Icons.Default.Delete, "Xóa"),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .background(Color.White)
            .padding(10.dp)
    ) {

        LazyColumn {
            items(listMenuProductDetails) { item ->
                Row(
                    modifier = Modifier
                        .clickable { /* Handle item click */ }
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black // Bạn có thể thay đổi màu sắc của icon nếu cần
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = item.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(500)
                    )
                }

                Divider()
            }
        }
    }
}

