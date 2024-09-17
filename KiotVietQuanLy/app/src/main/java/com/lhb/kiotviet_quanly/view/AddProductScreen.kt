package com.lhb.kiotviet_quanly.view

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.lhb.kiotviet_quanly.model.Product
import com.lhb.kiotviet_quanly.model.ProductType
import com.lhb.kiotviet_quanly.utils.formatCurrency
import com.lhb.kiotviet_quanly.view.components.CustomButtonBlue
import com.lhb.kiotviet_quanly.view.components.CustomTextEnter
import com.lhb.kiotviet_quanly.viewmodel.ProductTypeViewModel
import com.lhb.kiotviet_quanly.viewmodel.ProductViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddProductScreen(
    navController: NavController,
    productViewModel: ProductViewModel = viewModel(),
    productTypeViewModel: ProductTypeViewModel = viewModel(),
    product: Product? = null // Sản phẩm có thể null nếu là thêm mới
) {
    var nameProduct by remember { mutableStateOf(product?.name ?: "") }
    var quantity by remember { mutableStateOf(product?.quantity?.toString() ?: "") }
    var originalPrice by remember { mutableStateOf(product?.originalPrice?.let { formatCurrency(it) } ?: "") }
    var originalPriceRaw by remember { mutableStateOf(product?.originalPrice ?: 0) }
    var sellingPrice by remember { mutableStateOf(product?.price?.let { formatCurrency(it) } ?: "") }
    var sellingPriceRaw by remember { mutableStateOf(product?.price ?: 0) }
    var imageUri by remember { mutableStateOf<Uri?>(product?.image?.let { Uri.parse(it) }) }
    var selectedProductType by remember { mutableStateOf<ProductType?>(null) }
    var expanded by remember { mutableStateOf(false) }
    var isImageUpdated by remember { mutableStateOf(false) }

    val error by productViewModel.error.observeAsState()
    val context = LocalContext.current
    val listProductType by productTypeViewModel.productType.observeAsState(emptyList())
    val addProductSuccess by productViewModel.addProductSuccess.observeAsState()

    LaunchedEffect(product, listProductType) {
        if (product != null) {
            selectedProductType = listProductType.find { it.name == product.productType }
        }
    }

    LaunchedEffect(addProductSuccess) {
        if (addProductSuccess == true) {
            navController.popBackStack()
            productViewModel.clearAddProductSuccess()
        }
    }

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
            isImageUpdated = true
            Log.d("AddProductScreen", "Selected image URI: $uri") // Log selected URI
        }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            Row(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(0.dp)
                    .statusBarsPadding(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Outlined.ArrowBackIosNew,
                        contentDescription = "",
                        tint = Color(0xff454a4f),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                if (imageUri != null) {
                    Image(
                        painter = rememberImagePainter(data = imageUri),
                        contentDescription = null,
                        modifier = Modifier
                            .size(130.dp)
                            .clickable { launcher.launch("image/*") }
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
                    value = nameProduct,
                    onValueChange = { nameProduct = it },
                    label = "Nhập tên sản phẩm"
                )
                CustomTextEnter(
                    value = originalPrice,
                    onValueChange = {
                        val cleanInput = it.replace(",", "").toIntOrNull() ?: 0
                        originalPriceRaw = cleanInput
                        originalPrice = formatCurrency(cleanInput)
                    },
                    label = "Nhập giá gốc"
                )
                CustomTextEnter(
                    value = sellingPrice,
                    onValueChange = {
                        val cleanInput = it.replace(",", "").toIntOrNull() ?: 0
                        sellingPriceRaw = cleanInput
                        sellingPrice = formatCurrency(cleanInput)
                    },
                    label = "Nhập giá bán"
                )
                CustomTextEnter(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = "Số lượng"
                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        readOnly = true,
                        value = selectedProductType?.name ?: "Chọn loại sản phẩm",
                        onValueChange = {},
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .border(1.dp, Color(0xffb6b6b6), RoundedCornerShape(10.dp))
                            .clickable { expanded = !expanded },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        listProductType.forEach { productType ->
                            DropdownMenuItem(onClick = {
                                selectedProductType = productType
                                expanded = false
                            }) {
                                Text(text = productType.name ?: "")
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                CustomButtonBlue(
                    onClick = {
                        val qty = quantity.toIntOrNull() ?: 0

                        if (selectedProductType == null) {
                            Toast.makeText(context, "Vui lòng chọn loại sản phẩm", Toast.LENGTH_SHORT).show()
                        } else {
                            if (product == null) {
                                // Thêm sản phẩm mới
                                productViewModel.addProduct(
                                    imageUri, nameProduct, qty, originalPriceRaw,
                                    sellingPriceRaw, selectedProductType!!.name!!
                                )
                            } else {
                                if (isImageUpdated) { // Kiểm tra xem ảnh có được cập nhật hay không
                                    // Update product with a new image
                                    Log.d("AddProductScreenHAHA", "AddProductScreen: " + "oke")
                                    productViewModel.updateProductInDatabase(
                                        productId = product.id,
                                        nameProduct = nameProduct,
                                        quantity = qty,
                                        originalPrice = originalPriceRaw,
                                        sellingPrice = sellingPriceRaw,
                                        productType = selectedProductType!!.name!!,
                                        imageProduct = imageUri, // Sử dụng URI mới
                                        currentImageUrl = product.image // Giữ URL hiện tại làm backup
                                    )
                                } else {
                                    // Update product without changing the image
                                    Log.d("AddProductScreenHAHADMM", "AddProductScreen: " + "oke")
                                    productViewModel.updateProductInDatabase(
                                        productId = product.id,
                                        nameProduct = nameProduct,
                                        quantity = qty,
                                        originalPrice = originalPriceRaw,
                                        sellingPrice = sellingPriceRaw,
                                        productType = selectedProductType!!.name!!,
                                        imageProduct = null, // Không cập nhật ảnh
                                        currentImageUrl = product.image // Sử dụng URL hiện tại
                                    )
                                }
                            }
                        }
                    },
                    title = if (product == null) "Thêm sản phẩm" else "Cập nhật sản phẩm",
                )

                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        error?.let {
            Log.d("AddProductScreen", "Error: $it")
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            productViewModel.clearError()
        }
    }
}
