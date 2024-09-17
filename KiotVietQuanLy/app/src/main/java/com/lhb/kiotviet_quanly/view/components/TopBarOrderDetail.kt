package com.lhb.kiotviet_quanly.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lhb.kiotviet_quanly.R
import com.lhb.kiotviet_quanly.model.RetailCustomer


@Composable
fun TopBarOrderDetail(
    onClickToQR: () -> Unit,
    onClickToAdd: () -> Unit,
    clickRetailCustomer: () -> Unit,
    navController: NavController,
    nameCustomer: String ?= ""
){
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffF0F0F0))
            .shadow(3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 15.dp, vertical = 8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.Outlined.Cancel, contentDescription = "", tint = Color(0xff69717c), modifier = Modifier.size(24.dp)) }
                    Text(text = "Bán hàng", fontSize = 24.sp, color = Color(0xff303030), fontWeight = FontWeight.Bold)
                }
                IconButton(onClick = {  }) { Icon(Icons.Outlined.Assignment, contentDescription = "", tint = Color(0xff69717c), modifier = Modifier.size(24.dp)) }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color(0xfff0f0f0))
                        .padding(10.dp)
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Outlined.Search, contentDescription = "", tint = Color(0xff7d7f88), modifier = Modifier.size(22.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Tên, mã hàng, mã vạch, lô...",
                        color = Color(0xff7d7f88),
                        fontSize = 14.sp
                    )
                }
                IconButton(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xfff0f0f0)),
                    onClick = { /*TODO*/ }
                ) {
                    Icon( painter = painterResource(id = R.drawable.scan) , contentDescription = "", modifier = Modifier.size(20.dp))
                }
                IconButton(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xfff0f0f0)),
                    onClick = { /*TODO*/ }
                ) {
                    Icon( Icons.Outlined.Add , contentDescription = "", modifier = Modifier.size(24.dp), tint = Color(0xff303030))
                }
            }
            //
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
            ) {
                Row(
                    modifier = Modifier.clickable { clickRetailCustomer() }.height(40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        Icon(Icons.Outlined.Person, contentDescription = "", tint = Color(0xff404040), modifier = Modifier.size(20.dp))
                        Text(text = "Khách lẻ", color = Color(0xff404040), fontSize = 16.sp, modifier = Modifier.padding(horizontal = 10.dp), fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    if (nameCustomer != null) {
                        Text(text = nameCustomer, color = Color(0xff404040), fontSize = 16.sp, modifier = Modifier.padding(horizontal = 10.dp), fontWeight = FontWeight.Bold)
                    }
                    Icon(Icons.Outlined.ArrowForwardIos, contentDescription = "", tint = Color(0xff404040), modifier = Modifier.size(16.dp))
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Divider()
                Spacer(modifier = Modifier.padding(5.dp))
                Row(
                    modifier = Modifier.height(40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        Icon(painter = painterResource(id = R.drawable.price), contentDescription = "", tint = Color(0xff303030), modifier = Modifier.size(17.dp))
                        Text(text = "Bảng giá chung", color = Color(0xff404040), fontSize = 16.sp, modifier = Modifier.padding(horizontal = 12.dp), fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(Icons.Outlined.ArrowForwardIos, contentDescription = "", tint = Color(0xff404040), modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}
