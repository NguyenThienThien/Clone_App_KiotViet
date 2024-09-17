package com.lhb.kiotviet_quanly.view.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.CloseFullscreen
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lhb.kiotviet_quanly.R
import com.lhb.kiotviet_quanly.utils.formatCurrency
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarBill(
    onClickToSearch: () -> Unit,
    onClickToSort: () -> Unit,
    onClickToFilter: () -> Unit,
    onClickTopShowDateBottomSheet: () -> Unit,
    totalOrder: Int? = 0,
    totalAmount: Int? = 0,
    formattedDate: String? = null
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffF0F0F0))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Hóa đơn",
                    fontSize = 28.sp,
                    color = Color(0xff303030),
                    fontWeight = FontWeight.Bold
                )
                Row {
                    IconButton(onClick = { onClickToSearch() }) {
                        Icon(
                            Icons.Outlined.Search,
                            contentDescription = "",
                            tint = Color(0xff7d7f88),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = { onClickToSort() }) {
                        Icon(
                            Icons.Outlined.CloseFullscreen,
                            contentDescription = "",
                            tint = Color(0xff7d7f88),
                            modifier = Modifier.size(23.dp)
                        )
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
                    Icon(
                        Icons.Default.FilterList, contentDescription = "", tint = Color(0xff303030),
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
                        .clickable {
                            onClickTopShowDateBottomSheet()
                        }
                        .background(Color(0xffEFEFEF))
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formattedDate!!,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xff303030)
                    )
                    Icon(Icons.Outlined.ArrowDropDown, contentDescription = "", tint = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Tổng tiền hàng",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xff303030)
                        )
                        Icon(
                            Icons.Outlined.ArrowDropDown,
                            contentDescription = "",
                            tint = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        text = totalOrder.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xff404040)
                    )
                }
                Text(
                    text = formatCurrency(totalAmount!!),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xff303030)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xffF0F0F0))
                .height(15.dp)
        )
    }
}

@Composable
fun SearchBox(
    text: String,
    onTextChanged: (String) -> Unit,
    onClearClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChanged,
        placeholder = {
            Text(text = "Tìm kiếm...")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(15.dp)),
        shape = RoundedCornerShape(15.dp),
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(onClick = {
                    onClearClick()
                    onTextChanged("")
                }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Clear")
                }
            }
        },
        leadingIcon = {
            IconButton(onClick = onSearchClick) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White,
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.LightGray
        )
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun PreviewTopBarBill() {
    TopBarBill(
        onClickToSearch = { /*TODO*/ },
        onClickToSort = { /*TODO*/ },
        onClickToFilter = { /*TODO*/ },
        onClickTopShowDateBottomSheet = {}
    )
    SearchBox(text = "", onTextChanged = {}, onClearClick = { /*TODO*/ }) {

    }
}