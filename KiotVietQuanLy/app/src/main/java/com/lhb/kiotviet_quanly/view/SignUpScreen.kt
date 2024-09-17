package com.lhb.kiotviet_quanly.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.lhb.kiotviet_quanly.R
import com.lhb.kiotviet_quanly.utils.isLoggedIn
import com.lhb.kiotviet_quanly.utils.saveLoginState
import com.lhb.kiotviet_quanly.view.components.CustomBigButton
import com.lhb.kiotviet_quanly.view.components.CustomOutlinedTextField
import com.lhb.kiotviet_quanly.view.components.CustomOutlinedTextFieldPassword
import com.lhb.kiotviet_quanly.view.components.CustomTextFontSizeMedium

@Composable
fun SignUpScreen(navController: NavController) {

    val nameNationBusinessUser = remember { mutableStateOf("") }
    val phoneNumberUser = remember { mutableStateOf("") }
    val emailUser = remember { mutableStateOf("") }
    var passWordUser by remember { mutableStateOf("") }

    val nameNationBusinessUserFocused = remember { mutableStateOf(false) }
    val phoneNumberUserFocused = remember { mutableStateOf(false) }
    val emailUserFocused = remember { mutableStateOf(false) }

    val nameFocusRequester = remember { FocusRequester() }
    val phoneNumberFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(Color(0xffffffff))
                    .fillMaxWidth()
                    .padding(20.dp)
                    .statusBarsPadding()
            ) {
                Icon(
                    Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable { navController.popBackStack() },
                    tint = Color(0xff9fa5aa)
                )
                Spacer(modifier = Modifier.padding(15.dp))
                CustomTextFontSizeMedium(
                    title = "Tạo tài khoản dùng thử miễn phí",
                    color = Color.Black,
                    fontSize = 25
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {

            CustomOutlinedTextField(
                value = nameNationBusinessUser.value,
                onValueChange = { nameNationBusinessUser.value = it },
                label = "Tên gian hàng",
                isFocused = nameNationBusinessUserFocused,
                modifier = Modifier
                    .focusRequester(nameFocusRequester)
                    .onFocusChanged { nameNationBusinessUserFocused.value = it.isFocused }
            )

            CustomOutlinedTextField(
                value = phoneNumberUser.value,
                onValueChange = { phoneNumberUser.value = it },
                label = "Nhập số điện thoại",
                isFocused = phoneNumberUserFocused,
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
                },
                modifier = Modifier
                    .focusRequester(phoneNumberFocusRequester)
                    .onFocusChanged { phoneNumberUserFocused.value = it.isFocused },
            )

            CustomOutlinedTextField(
                value = emailUser.value,
                onValueChange = { emailUser.value = it },
                label = "Nhập email của gian hàng",
                isFocused = emailUserFocused,
                modifier = Modifier
                    .focusRequester(emailFocusRequester)
                    .onFocusChanged { emailUserFocused.value = it.isFocused }
            )

            Spacer(modifier = Modifier.padding(5.dp))

            CustomOutlinedTextFieldPassword(
                value = passWordUser,
                onValueChange = { passWordUser = it },
                label = "Mật khẩu",
                modifier = Modifier
                    .focusRequester(passwordFocusRequester)
            )

            Spacer(modifier = Modifier.padding(top = 30.dp))

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

            Spacer(modifier = Modifier.padding(top = 20.dp))

            CustomBigButton(
                title = "Tiếp tục",
                onClick = {
                    if(validateInput(nameNationBusinessUser.value, phoneNumberUser.value, emailUser.value, passWordUser)){
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailUser.value, passWordUser).addOnCompleteListener{ task ->
                            if(task.isSuccessful){
                                var userId = FirebaseAuth.getInstance().currentUser?.uid
                                val database = FirebaseDatabase.getInstance()
                                val userRef = database.getReference("Users").child(userId ?: "")
                                val userData = mapOf(
                                    "id" to userId,
                                    "Username" to nameNationBusinessUser.value,
                                    "PhoneNumber" to phoneNumberUser.value,
                                    "EmailUser" to emailUser.value
                                )

                                userRef.setValue(userData).addOnCompleteListener{dbTask ->
                                    if(dbTask.isSuccessful){
                                        saveLoginState(context, true)
                                        Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_LONG).show()
                                        navController.navigate("BottomTav")
                                    }else{
                                        Toast.makeText(context, "Lưu dữ liệu thất bại: ${dbTask.exception?.message}", Toast.LENGTH_LONG).show()
                                    }
                                }

                            }else{
                                Toast.makeText(context, "Đăng ký thất bại: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                            }
                        }
                    } else {
                        when {
                            nameNationBusinessUser.value.isEmpty() -> {
                                nameFocusRequester.requestFocus()
                                Toast.makeText(context, "Vui lòng nhập tên gian hàng", Toast.LENGTH_SHORT).show()
                            }
                            !isValidPhoneNumber(phoneNumberUser.value) -> {
                                phoneNumberFocusRequester.requestFocus()
                                Toast.makeText(context, "Số điện thoại không đúng định dạng", Toast.LENGTH_SHORT).show()
                            }
                            !isValidEmail(emailUser.value) -> {
                                emailFocusRequester.requestFocus()
                                Toast.makeText(context, "Email không đúng định dạng", Toast.LENGTH_SHORT).show()
                            }
                            passWordUser.isEmpty() -> {
                                passwordFocusRequester.requestFocus()
                                Toast.makeText(context, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            )

        }
    }
}

fun validateInput(nationBusiness: String, phone: String, email: String, password: String): Boolean {
    return nationBusiness.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() &&
            isValidEmail(email) && isValidPhoneNumber(phone)
}

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidPhoneNumber(phone: String): Boolean {
    return android.util.Patterns.PHONE.matcher(phone).matches()
}