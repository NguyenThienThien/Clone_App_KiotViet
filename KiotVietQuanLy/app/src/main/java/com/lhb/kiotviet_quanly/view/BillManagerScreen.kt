package com.lhb.kiotviet_quanly.view

import BillViewModel
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lhb.kiotviet_quanly.view.component.ItemBill
import com.lhb.kiotviet_quanly.view.components.SearchBox
import com.lhb.kiotviet_quanly.view.components.TopBarBill
import com.lhb.kiotviet_quanly.view.navigation.BottomBarScreens
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BillManagerScreen(
    navController: NavController,
    navigateToScreen: (ImageVector, String) -> Unit,
    billViewModel: BillViewModel = viewModel()
) {
    val bills by billViewModel.searchResults.observeAsState(emptyList())
    val isLoading by billViewModel.isLoading.observeAsState(false)
    val totalAmount = bills.sumOf { it.totalAmount ?: 0 }
    var isSearchActive by remember { mutableStateOf(false) }
    var searchByName by remember { mutableStateOf("") }

    val calendarState = rememberSheetState()
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val formattedDate = "${selectedDate.monthValue}/${selectedDate.year}"

    LaunchedEffect(selectedDate) {
        val month = selectedDate.monthValue
        val year = selectedDate.year
        // Chờ dữ liệu tải xong trước khi lọc
        if (!isLoading) {
            billViewModel.filterBillsByMonth(month, year)
        }
        Log.d("BillManagerScreen", "Selected date: $selectedDate")
    }

    LaunchedEffect(searchByName) {
        billViewModel.searchBills(searchByName)
        Log.d("BillManagerScreen", "Search by name: $searchByName")
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
            .navigationBarsPadding()
        ,
        topBar = {
            AnimatedVisibility(
                visible = isSearchActive,
                enter = fadeIn(animationSpec = tween(durationMillis = 300)) + scaleIn(animationSpec = tween(durationMillis = 300)),
                exit = fadeOut(animationSpec = tween(durationMillis = 300)) + scaleOut(animationSpec = tween(durationMillis = 300))
            ) {
                SearchBox(
                    text = searchByName,
                    onTextChanged = { searchByName = it },
                    onClearClick = {
                        searchByName = ""
                        isSearchActive = false
                        billViewModel.searchBills(searchByName)
                    },
                    onSearchClick = {
                        billViewModel.searchBills(searchByName)
                    }
                )
            }

            AnimatedVisibility(
                visible = !isSearchActive,
                enter = fadeIn(animationSpec = tween(durationMillis = 300)) + scaleIn(animationSpec = tween(durationMillis = 300)),
                exit = fadeOut(animationSpec = tween(durationMillis = 300)) + scaleOut(animationSpec = tween(durationMillis = 300))
            ) {
                TopBarBill(
                    onClickToSearch = {
                        isSearchActive = true
                    },
                    onClickToSort = { /*TODO*/ },
                    onClickToFilter = { /*TODO*/ },
                    onClickTopShowDateBottomSheet = {
                        calendarState.show()
                    },
                    totalOrder = bills.size,
                    totalAmount = totalAmount,
                    formattedDate = formattedDate
                )
            }
        },
        floatingActionButton = {
            IconButton(
                onClick = { navigateToScreen(Icons.Outlined.ShoppingBag, BottomBarScreens.SellManager.screen) },
                modifier = Modifier
                    .padding(bottom = 70.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color(0xff014e8a))
                    .padding(5.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "",
                    modifier = Modifier.size(28.dp),
                    tint = Color.White
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .pointerInput(Unit) {
                    detectTapGestures {
                        if (isSearchActive) {
                            isSearchActive = false
                        }
                    }
                }
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (bills.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(bills, key = { it.id!! }) { bill ->
                        ItemBill(bill = bill)
                    }
                    item {
                        Box(modifier = Modifier.height(50.dp))
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No bills available")
                }
            }
        }
    }
}
