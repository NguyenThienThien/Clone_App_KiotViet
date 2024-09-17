package com.lhb.kiotviett.View

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lhb.kiotviett.R
import com.lhb.kiotviett.View.component.ChooseProductGroup
import com.lhb.kiotviett.View.component.CommodityGroup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProductScreen(navController: NavController) {

    var productName by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var capitalprice by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    val sheetState = androidx.compose.material3.rememberModalBottomSheetState()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    var showBottomSheetCamera by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFf5f5f5))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
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
                    text = "Thêm hàng hóa",
                    color = Color(0xff303030),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }

        Divider()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 10.dp, end = 10.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_nameproduct),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Tên hàng hóa",
                    color = Color(0xff303030),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "*",
                    color = Color.Red,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.weight(1f))

                TextField(
                    value = productName,
                    onValueChange = { newValue -> productName = newValue },
                    placeholder = {
                        Text(
                            text = "Nhập tên hàng hóa",
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .width(200.dp)
                )
            }
            Divider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable { navController.navigate("ChooseProduct") },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_group),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Nhóm hàng",
                    color = Color(0xff303030),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "*",
                    color = Color.Red,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    Icons.Default.ArrowForwardIos,
                    contentDescription = null,
                    tint = Color(0xFF50555a),
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
            Divider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_sellingprice),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Giá bán",
                    color = Color(0xff303030),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.weight(1f)) // Push TextField to the right

                TextField(
                    value = price,
                    onValueChange = { newValue -> price = newValue },
                    placeholder = {
                        Text(
                            text = "0",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                            fontSize = 16.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .width(200.dp)
                )
            }
            Divider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable { showBottomSheet = true },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_producttype),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Loại thực đơn",
                    color = Color(0xff303030),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.weight(1f)) // Push TextField to the right

                Icon(
                    Icons.Default.ArrowForwardIos,
                    contentDescription = null,
                    tint = Color(0xFF50555a),
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
            Divider()
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_capitalprice),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Giá vốn",
                    color = Color(0xff303030),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.weight(1f)) // Push TextField to the right

                TextField(
                    value = capitalprice,
                    onValueChange = { newValue -> capitalprice = newValue },
                    placeholder = {
                        Text(
                            text = "0",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                            fontSize = 16.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .width(200.dp)
                )
            }
            Divider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_inventory),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Số lượng tồn kho",
                    color = Color(0xff303030),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.weight(1f)) // Push TextField to the right

                TextField(
                    value = quantity,
                    onValueChange = { newValue -> quantity = newValue },
                    placeholder = {
                        Text(
                            text = "1",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                            fontSize = 16.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .width(200.dp),
                )
            }
            Divider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_address),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Vị trí",
                    color = Color(0xff303030),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.weight(1f)) // Push TextField to the right

                Icon(
                    Icons.Default.ArrowForwardIos,
                    contentDescription = null,
                    tint = Color(0xFF50555a),
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
            Divider()
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(10.dp)
        ) {
            Button(
                onClick = { showBottomSheetCamera = true },
                modifier = Modifier
                    .width(130.dp)
                    .height(130.dp)
                    .shadow(3.dp, RoundedCornerShape(20.dp))
                    .background(Color.White),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFFFFFFF)
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.AddAPhoto,
                        contentDescription = null,
                        tint = Color(0xFF0066c7),
                        modifier = Modifier.size(30.dp)
                    )

                    Text(
                        text = "Thêm ảnh",
                        color = Color(0xff303030),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Spacer(modifier = Modifier.weight(1f))
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
                    Text(
                        text = "Lưu lại",
                        color = Color(0xffFFFFFF),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Spacer(modifier = Modifier.padding(15.dp))
            }

        }
    }

    if(showBottomSheet){
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            dragHandle = {}
        ){
            Column(
                modifier = Modifier
                    .navigationBarsPadding()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CommodityGroup {
                    showBottomSheet = false
                }
            }
        }
    }

    if(showBottomSheetCamera){
        ModalBottomSheet(
            onDismissRequest = { showBottomSheetCamera = false },
            sheetState = sheetState,
            dragHandle = {},
            containerColor = Color.Transparent
        ){
            Column(
                modifier = Modifier
                    .navigationBarsPadding()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                BottomSheetCamera()
            }
        }
    }
}

@Composable
fun BottomSheetCamera(){
    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(10.dp)
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
        ) {

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xffefeff1)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RectangleShape
            ) {
                Text(
                    text = "Chụp từ Camera",
                    fontSize = 16.sp,
                    color = Color(0xff0081ff),
                    fontWeight = FontWeight(500)
                )
            }

            Divider()

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xffefeff1)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RectangleShape
            ) {
                Text(
                    text = "Chọn từ thư viện",
                    fontSize = 16.sp,
                    color = Color(0xff0081ff),
                    fontWeight = FontWeight(500)
                )
            }
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xffffffff)
            ),
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(5.dp)),
        ) {
            Text(
                text = "Hủy",
                fontSize = 22.sp,
                color = Color(0xff0081ff),
                fontWeight = FontWeight.Bold
            )
        }

    }

}

@Composable
@Preview
fun PreviewCreateProductScreen() {
    CreateProductScreen(navController = rememberNavController())
}