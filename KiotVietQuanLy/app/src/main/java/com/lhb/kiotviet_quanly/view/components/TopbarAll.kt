package com.lhb.kiotviet_quanly.view.components

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.CloseFullscreen
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lhb.kiotviet_quanly.R

@Composable
fun TopBarAll(
    onClickToSearch: () -> Unit,
    onClickToSort: () -> Unit,
    onClickToFilter: () -> Unit,
    onClickTopShowDateBottomSheet: () -> Unit,
    onClickToBack: () -> Unit,
    title: String
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffF0F0F0))
            .statusBarsPadding()
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
                    IconButton(onClick = { onClickToBack() }) {
                        Icon(Icons.Outlined.ArrowBackIosNew, contentDescription = "", tint = Color(0xff7d7f88), modifier = Modifier.size(24.dp))
                    }
                    Text(text = title, fontSize = 28.sp, color = Color(0xff303030), fontWeight = FontWeight.Bold)
                }

                Row {
                    IconButton(onClick = { onClickToSearch() }) {
                        Icon(Icons.Outlined.Search, contentDescription = "", tint = Color(0xff7d7f88), modifier = Modifier.size(24.dp))
                    }
                    IconButton(onClick = { onClickToSort() }) {
                        Icon(Icons.Outlined.CloseFullscreen, contentDescription = "", tint = Color(0xff7d7f88), modifier = Modifier.size(23.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { onClickToFilter() }) {
                    Icon(Icons.Default.FilterList, contentDescription = "", tint = Color(0xff303030),
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xffb6b6b6).copy(alpha = 0.3f))
                            .padding(5.dp)
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color(0xffEFEFEF))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .clickable { onClickTopShowDateBottomSheet() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Tháng này", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xff303030))
                    Icon(Icons.Outlined.ArrowDropDown, contentDescription = "", tint = Color.Gray)
                }
            }
        }
    }
}