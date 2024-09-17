package com.lhb.kiotviet_quanly.view

import BillViewModel
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.OpenInFull
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.faskn.lib.PieChart
import com.lhb.kiotviet_quanly.R
import com.lhb.kiotviet_quanly.view.components.ItemOverView
import com.lhb.kiotviet_quanly.view.components.TopBarOverView
import com.lhb.kiotviet_quanly.viewmodel.OverViewManagerViewModel
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OverViewManagerScreen(
    navController: NavController,
    overViewManagerViewModel: OverViewManagerViewModel = viewModel()
){
    val bills by overViewManagerViewModel.searchResults.observeAsState(emptyList())
    val totalBill = bills.sumOf { it.totalAmount!! }
    val totalAmountFormatted = (totalBill / 1_000_000f).toString()

    val totalProfit = bills.sumOf { it.profit ?: 0 }
    val totalProfitFormatted = (totalProfit / 1_000_000f).toString()

    var isVisibleLoiNhuan by remember { mutableStateOf(false) }
    val isLoading by overViewManagerViewModel.isLoading.observeAsState(false)
    val calendarState = rememberSheetState()
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val formattedDate = "${selectedDate.dayOfMonth}/${selectedDate.monthValue}/${selectedDate.year}"

    LaunchedEffect(selectedDate) {
        val day = selectedDate.dayOfMonth
        val month = selectedDate.monthValue
        val year = selectedDate.year
        if (!isLoading) {
            overViewManagerViewModel.filterBillsByMonthDay(day,month,year)
        }
        Log.d("BillManagerScreen", "Selected date: $selectedDate")
    }

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
            style = CalendarStyle.MONTH,
            disabledDates = listOf(LocalDate.now())
        ),
        selection = CalendarSelection.Date { date ->
            selectedDate = date
        }
    )

    Scaffold(
        containerColor = Color(0xffF0F0F0),
        modifier = Modifier
            .navigationBarsPadding(),
        topBar = {
            TopBarOverView(
                onClickToNotification = { /*TODO*/ },
                onClickToEmail = { /*TODO*/ },
                onClickTopShowBottomSheetDate = { calendarState.show() },
                formattedDate = formattedDate
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(15.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(5.dp)
                                )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(10.dp)
                                ) {
                                    Text(text = "${bills.size} hóa đơn", fontSize = 15.sp, color = Color(0xff686868))
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(
                                        text = buildAnnotatedString {
                                            append(totalAmountFormatted)  // Sử dụng tổng số tiền đã định dạng
                                            withStyle(style = SpanStyle(color = Color(0xff303030), fontWeight = FontWeight.Medium, fontSize = 15.sp)) {
                                                append(" triệu")  // Đơn vị tiền tệ
                                            }
                                        },
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xff0066CC),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                                Column(
                                    modifier = Modifier.weight(1f),
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.Top
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = "Lợi nhuận", fontSize = 15.sp, color = Color(0xff686868))
                                        Spacer(modifier = Modifier.width(5.dp))
                                        IconButton(onClick = { isVisibleLoiNhuan = !isVisibleLoiNhuan }) {
                                            Icon(
                                                if(isVisibleLoiNhuan) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                                                contentDescription = "",
                                                tint = Color(0xff686868),
                                                modifier = Modifier.size(23.dp)
                                            )
                                        }
                                    }
                                    Text(
                                        text = buildAnnotatedString {
                                            if(isVisibleLoiNhuan){
                                                append(totalProfitFormatted)
                                                withStyle(style = SpanStyle(color = Color(0xff303030), fontWeight = FontWeight.Medium, fontSize = 15.sp)){
                                                    append(" triệu")
                                                }
                                            }else{
                                                append("*** ***")
                                            }
                                        },
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xff32CD32),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                            Divider(color = Color.LightGray, thickness = 0.5.dp, modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, bottom = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.icon_box_back),
                                    contentDescription = "",
                                    modifier = Modifier.height(35.dp)
                                )
                                Text(
                                    text = buildAnnotatedString {
                                        append("0")
                                        withStyle(
                                            style = SpanStyle(
                                                color = Color(0xff686868),
                                                fontWeight = FontWeight.Normal,
                                                fontSize = 15.sp
                                            )
                                        ) {
                                            append(" phiếu trả hàng")
                                        }
                                    },
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xff303030),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 10.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            ItemOverView(iconName = painterResource(id = R.drawable.money_bag), title = "Vay vốn",26, onCLick = {})
                            ItemOverView(iconName = painterResource(id = R.drawable.icon_delivery), title = "Giao hàng",26,onCLick = {})
                            ItemOverView(iconName = painterResource(id = R.drawable.icon_cart), title = "Nguồn hàng sỉ",26,onCLick = {})
                        }
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(15.dp)
                    ) {
                        Text(text = "Doanh thu", fontSize = 18.sp, color = Color(0xff303030), fontWeight = FontWeight.Bold)
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 15.dp)
                        ){
                          IconButton(onClick = { /*TODO*/ }) {
                              Icon(Icons.Default.BarChart, contentDescription = "", tint = Color(0xff0066CC),
                                  modifier = Modifier
                                      .clip(RoundedCornerShape(20.dp))
                                      .background(Color(0xff0066CC).copy(alpha = 0.3f))
                                      .padding(5.dp)
                              )
                          }
                            Spacer(modifier = Modifier.width(20.dp))
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Default.OpenInFull, contentDescription = "", tint = Color(0xff303030),)
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .height(300.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50.dp))
                                    .background(Color(0xff0066CC).copy(alpha = 0.1f))
                                    .padding(15.dp),
                                contentAlignment = Alignment.Center
                            ){
                                Icon(painter = painterResource(id = R.drawable.graph), contentDescription = "", tint = Color(0xff0066CC),
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(RoundedCornerShape(40.dp))
                                        .background(Color(0xff0066CC).copy(alpha = 0.2f))
                                        .padding(15.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(text = "Bạn chưa bán đơn nào", fontSize = 16.sp, color = Color(0xffb6b6b6), fontWeight = FontWeight.Normal)
                        }
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Tồn kho", fontSize = 18.sp, color = Color(0xff303030), fontWeight = FontWeight.Bold)
                        Column(
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(text = "27,600,000", fontSize = 16.sp, color = Color(0xff303030), fontWeight = FontWeight.Medium)
                            Text(text = "184 sản phẩm", fontSize = 14.sp, color = Color(0xff303030), fontWeight = FontWeight.Normal)
                        }
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(15.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Thu", fontSize = 18.sp, color = Color(0xff303030), fontWeight = FontWeight.Bold)
                            Column(
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(text = "0", fontSize = 16.sp, color = Color(0xff303030), fontWeight = FontWeight.Medium)
                                Text(text = "phiếu", fontSize = 14.sp, color = Color(0xff303030), fontWeight = FontWeight.Normal)
                            }
                        }
                        Divider(color = Color.LightGray, thickness = 0.5.dp, modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Chi", fontSize = 18.sp, color = Color(0xff303030), fontWeight = FontWeight.Bold)
                            Column(
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(text = "0", fontSize = 16.sp, color = Color(0xff303030), fontWeight = FontWeight.Medium)
                                Text(text = "phiếu", fontSize = 14.sp, color = Color(0xff303030), fontWeight = FontWeight.Normal)
                            }
                        }
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(15.dp)
                    ) {
                        Text(text = "Hàng bán chạy", fontSize = 18.sp, color = Color(0xff303030), fontWeight = FontWeight.Bold)
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .height(300.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50.dp))
                                    .background(Color(0xff0066CC).copy(alpha = 0.1f))
                                    .padding(15.dp),
                                contentAlignment = Alignment.Center
                            ){
                                Icon(painter = painterResource(id = R.drawable.product), contentDescription = "", tint = Color(0xff0066CC),
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(RoundedCornerShape(40.dp))
                                        .background(Color(0xff0066CC).copy(alpha = 0.2f))
                                        .padding(15.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(text = "Bạn chưa bán hàng", fontSize = 16.sp, color = Color(0xffb6b6b6), fontWeight = FontWeight.Normal)
                        }
                    }
                }
            }
        }
    }
}
