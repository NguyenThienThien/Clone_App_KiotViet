import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.lhb.kiotviet_quanly.view.components.CustomButtonBlue
import com.lhb.kiotviet_quanly.view.components.CustomTextEnter
import com.lhb.kiotviet_quanly.viewmodel.RetailCustomerViewModel

@Composable
fun DialogAddRetailCustomer(onDismiss: () -> Unit) {
    val retailCustomerViewModel: RetailCustomerViewModel = viewModel()
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        retailCustomerViewModel.onImageSelected(uri)
    }

    if (retailCustomerViewModel.isCustomerAdded) {
        Toast.makeText(context, "Thêm khách hàng thành công!", Toast.LENGTH_SHORT).show()
        onDismiss()
        retailCustomerViewModel.isCustomerAdded = false
    }

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        if (retailCustomerViewModel.imageUri != null) {
            Image(
                painter = rememberImagePainter(data = retailCustomerViewModel.imageUri),
                contentDescription = null,
                modifier = Modifier
                    .size(130.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .shadow(3.dp, RoundedCornerShape(20.dp))
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )
        } else {
            Button(
                onClick = { launcher.launch("image/*") },
                modifier = Modifier
                    .width(130.dp)
                    .height(130.dp)
                    .shadow(3.dp, RoundedCornerShape(20.dp))
                    .background(Color.White),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                )
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
        }

        Spacer(modifier = Modifier.height(10.dp))

        CustomTextEnter(
            value = retailCustomerViewModel.username,
            onValueChange = { retailCustomerViewModel.onUsernameChanged(it)},
            label = "Nhập tên khách hàng"
        )
        CustomTextEnter(
            value = retailCustomerViewModel.phone,
            onValueChange = { retailCustomerViewModel.onPhoneChanged(it) },
            label = "Nhập số điện thoại"
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomButtonBlue(
            onClick = { retailCustomerViewModel.addCustomer() },
            title = "Thêm khách hàng",
            isLoading = retailCustomerViewModel.isLoading // Truyền trạng thái loading vào
        )

        retailCustomerViewModel.errorMessage?.let { error ->
            Text(text = error, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}
