package com.lhb.kiotviett.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.RadioButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lhb.kiotviett.View.component.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGroupProduct(navController: NavController) {

    var groupName by remember { mutableStateOf("") }
    var selectedParentGroup by remember { mutableStateOf("Chọn nhóm cha") }
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.Outlined.Close, contentDescription = "",
                            modifier = Modifier.size(26.dp),
                            tint = Color(0xff303030)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Tạo nhóm hàng",
                        color = Color(0xff303030),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier.padding(10.dp).navigationBarsPadding()
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFaeceee)
                    )
                ) {
                    androidx.compose.material.Text(
                        text = "Lưu lại",
                        color = Color(0xffFFFFFF),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Spacer(modifier = Modifier.padding(15.dp))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Row(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = "Tên nhóm",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xff303030)
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "*",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color.Red
                )
            }

            TextField(
                value = groupName,
                onValueChange = { newValue -> groupName = newValue },
                placeholder = {
                    androidx.compose.material.Text(
                        text = "Nhập tên hàng hóa",
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xfff5f5f5),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Row(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = "Nhóm cha",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xff303030)
                )
                Spacer(modifier = Modifier.padding(2.dp))
            }

            Spacer(modifier = Modifier.padding(5.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xfff5f5f5))
                    .clickable { showBottomSheet = true },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedParentGroup,
                    color = Color(0xff070d12),
                    fontSize = 15.sp,
                    fontWeight = FontWeight(500),
                    modifier = Modifier.padding(start = 18.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(25.dp)
                )
            }

            if (showBottomSheet) {
                val configuration = LocalConfiguration.current
                val screenHeight = configuration.screenHeightDp.dp
                val bottomSheetHeight = screenHeight * 0.8f
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    dragHandle = {},
                    containerColor = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .navigationBarsPadding()
                            .fillMaxWidth()
                            .height(bottomSheetHeight),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        ShowBottomSheet { selectedGroup ->
                            selectedParentGroup = selectedGroup
                            showBottomSheet = false
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun ShowBottomSheet(onClick: (String) -> Unit) {
    val listProductGroup = listOf(
        "BIA & THUỐC LÁ", "CLASSIC COCKTAILS", "MÓN KHAI VỊ", "SÚP", "TEA"
    )
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            androidx.compose.material.Text(
                text = "Chọn nhóm cha",
                color = Color(0xff303030),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
            )
            IconButton(
                onClick = { },
                modifier = Modifier
                    .padding(start = 5.dp)
            ) {
                androidx.compose.material.Icon(
                    Icons.Outlined.Close, contentDescription = "",
                    modifier = Modifier.size(26.dp),
                    tint = Color(0xff303030)
                )
            }
        }
        Divider()
        LazyColumn {
            items(listProductGroup.size) { index ->
                Row(
                    modifier = Modifier
                        .clickable { onClick(listProductGroup[index]) }
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = listProductGroup[index],
                        color = Color(0xff303030),
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                    )
                }
                Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                    Divider()
                }
            }
        }
    }

}

@Composable
@Preview
fun PreviewCreateGroupProduct() {
    CreateGroupProduct(navController = rememberNavController())
}