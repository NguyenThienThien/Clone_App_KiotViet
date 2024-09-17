package com.lhb.kiotviett.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lhb.kiotviett.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController: NavController){

    val nameUser = remember { mutableStateOf("") }
    val phoneNumberUser = remember { mutableStateOf("") }
    val nationalityUser = remember { mutableStateOf("") }
    val areaUser = remember { mutableStateOf("") }

    val nameUserFocused = remember { mutableStateOf(false) }
    val phoneNumberUserFocused = remember { mutableStateOf(false) }
    val nationalityUserFocused = remember { mutableStateOf(false) }
    val areaUserFocused = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp)
            .statusBarsPadding()
    ) {
        Spacer(modifier = Modifier.padding(top = 10.dp))
        IconButton(
            onClick = {
            navController.popBackStack()
        }) {
            Icon(
                Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Text(
            text = "Tạo tài khoản dùng thử miễn phí",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(5.dp)
        )

        CustomOutlinedTextField(
            value = nameUser.value,
            onValueChange = { nameUser.value = it },
            label = "Nhập họ tên",
            isFocused = nameUserFocused
        )

        CustomOutlinedTextField(
            value = nameUser.value,
            onValueChange = { nameUser.value = it },
            label = "Nhập số điện thoại",
            isFocused = nameUserFocused,
            leadingIcon = {
                Row(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_vn),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
        )

        CustomOutlinedTextField(
            value = nameUser.value,
            onValueChange = { nameUser.value = it },
            label = "Quốc gia đang kinh doanh",
            isFocused = nameUserFocused
        )

        CustomOutlinedTextField(
            value = nameUser.value,
            onValueChange = { nameUser.value = it },
            label = "Khu vực",
            isFocused = nameUserFocused
        )

        Spacer(modifier = Modifier.padding(top = 20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bằng việc nhấn nút Tiếp tục đã đồng ý với",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp)
            )

            Text(
                text = "Điều khoản và chính sách sử dụng",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xff005595)
            )
        }

        Spacer(modifier = Modifier.padding(top = 10.dp))

        CustomBigButton(
            title = "Tiếp tục",
            onClick = {
                navController.popBackStack()
            }
        )


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isFocused: MutableState<Boolean>,
    leadingIcon: @Composable (() -> Unit)? = null // Optional icon parameter
) {
    Spacer(modifier = Modifier.padding(top = 10.dp))
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, fontSize = 16.sp) },
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .onFocusChanged { isFocused.value = it.isFocused },
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        leadingIcon = leadingIcon, // Add leadingIcon parameter
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xff005595),
            unfocusedBorderColor = Color(0xFFD2D2D2),
            containerColor = Color.Transparent
        )
    )
}

@Composable
fun CustomBigButton(
    onClick: () -> Unit = {},
    title: String
){
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff005595)) // Color: 312064
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}



@Composable
@Preview
fun PreviewSignUp(){
    SignUp(navController = rememberNavController())
}