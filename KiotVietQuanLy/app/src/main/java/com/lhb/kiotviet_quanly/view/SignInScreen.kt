package com.lhb.kiotviet_quanly.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.lhb.kiotviet_quanly.R
import com.lhb.kiotviet_quanly.ui.theme.OpenSans
import com.lhb.kiotviet_quanly.utils.saveLoginState
import com.lhb.kiotviet_quanly.view.components.BottomSheetPhoneNumber
import com.lhb.kiotviet_quanly.view.components.CustomBigButton
import com.lhb.kiotviet_quanly.view.components.CustomOutlinedTextField
import com.lhb.kiotviet_quanly.view.components.CustomOutlinedTextField1
import com.lhb.kiotviet_quanly.view.components.CustomOutlinedTextFieldPassword
import com.lhb.kiotviet_quanly.view.components.CustomTextFontSize
import com.lhb.kiotviet_quanly.view.components.CustomTextFontSizeMedium
import com.lhb.kiotviet_quanly.view.components.CustomTextOnClick

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController){
    var stallName by remember { mutableStateOf("") }
    var loginName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .statusBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_welcome),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .padding(10.dp)
                )
                Spacer(modifier = Modifier.padding(7.dp))
                Box(
                    modifier = Modifier
                        .height(35.dp)
                        .width(140.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFe7f2fa)),
                    contentAlignment = Alignment.Center
                ) {
                    CustomTextFontSizeMedium(title = "KiotViet Quản lý", color = Color(0xFF2880c6))
                }
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(10.dp)
                    .navigationBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_haha),
                    contentDescription = "",
                    modifier = Modifier
                        .width(130.dp)
                        .height(50.dp)
                )

                Spacer(modifier = Modifier.padding(5.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Phone, contentDescription = "", modifier = Modifier.size(20.dp), tint = Color(0xFF71727b))
                    Spacer(modifier = Modifier.padding(5.dp))
                    CustomTextFontSize(title = "Hỗ trợ", color = Color(0xFF71727b), fontSize = 18)
                    Spacer(modifier = Modifier.padding(5.dp))
                    CustomTextOnClick(title = "1900 6522", color = Color(0xFF569ef7), fontSize = 18) { showBottomSheet = true }
                }
            }
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    dragHandle = {},
                    containerColor = Color.Transparent
                ) {
                    BottomSheetPhoneNumber {
                        showBottomSheet = false
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Spacer(modifier = Modifier.padding(15.dp))
                CustomOutlinedTextField1(value = stallName, onValueChange = {stallName = it}, label = "Tên gian hàng", modifier = Modifier)
                Spacer(modifier = Modifier.padding(5.dp))
                CustomOutlinedTextField(value = loginName, onValueChange = {loginName = it}, label = "Email đăng nhập", modifier = Modifier)
                Spacer(modifier = Modifier.padding(5.dp))
                CustomOutlinedTextFieldPassword(value = password, onValueChange = {password = it}, label = "Mật khẩu", modifier = Modifier)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Quên mât khẩu ?", fontSize = 16.sp, color = Color(0xFF2880c6), fontFamily = OpenSans, fontWeight = FontWeight(600), textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth().clickable { navController.navigate("ForgotPasswordScreen") })
                Spacer(modifier = Modifier.padding(10.dp))
                CustomBigButton(
                    title = "Đăng nhập",
                    onClick = {
                        if (validateInput(stallName, loginName, password)) {
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(loginName, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        saveLoginState(context, true) // Lưu trạng thái đăng nhập
                                        val userId = FirebaseAuth.getInstance().currentUser?.uid
                                        if (userId != null) {
                                            val database = FirebaseDatabase.getInstance()
                                            val userRef = database.getReference("Users").child(userId)
                                            userRef.get().addOnSuccessListener { snapshot ->
                                                val storedStallName = snapshot.child("Username").getValue(String::class.java)
                                                if (storedStallName == stallName) {
                                                    Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_LONG).show()
                                                    navController.navigate("BottomTav")
                                                } else {
                                                    Toast.makeText(context, "Tên gian hàng không khớp", Toast.LENGTH_LONG).show()
                                                }
                                            }.addOnFailureListener {
                                                Toast.makeText(context, "Lấy dữ liệu thất bại: ${it.message}", Toast.LENGTH_LONG).show()
                                            }
                                        }
                                    } else {
                                        Toast.makeText(context, "Đăng nhập thất bại: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                                    }
                                }
                        } else {
                            Toast.makeText(context, "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Spacer(modifier = Modifier.width(10.dp))
                    CustomTextFontSize(title = "Bạn chưa có tài khoản ?", color = Color(0xFF71727b), fontSize = 16)
                    Spacer(modifier = Modifier.width(10.dp))
                    CustomTextOnClick(title = "Đăng ký", color = Color(0xFF0971e9), fontSize = 16, onclick = { navController.navigate("SignUpScreen") })
                }
            }
        }
    }
}

fun validateInput(stallName: String, loginName: String, password: String): Boolean {
    return stallName.isNotEmpty() && loginName.isNotEmpty() && password.isNotEmpty() &&
            android.util.Patterns.EMAIL_ADDRESS.matcher(loginName).matches()
}

@Composable
@Preview
fun SignInScreenPreview() {
    SignInScreen(navController = rememberNavController())
}